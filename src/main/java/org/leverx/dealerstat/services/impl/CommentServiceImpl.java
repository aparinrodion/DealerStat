package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.exceptions.UserIsNotOwnerOfCommentException;
import org.leverx.dealerstat.mappers.CommentMapper;
import org.leverx.dealerstat.model.Comment;
import org.leverx.dealerstat.model.User;
import org.leverx.dealerstat.repositories.CommentsRepository;
import org.leverx.dealerstat.services.CommentService;
import org.leverx.dealerstat.services.RegistrationService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final RegistrationService registrationService;

    @Override
    public List<CommentDto> getAll() {
        List<Comment> comments = new ArrayList<>();
        commentsRepository.findAll().forEach(comments::add);
        return comments.stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getById(Integer id) {
        return commentMapper.mapToDto(commentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Comment.class, id)));
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        if (!userService.existsById(commentDto.getTraderId())) {
            throw new EntityNotFoundException(User.class, commentDto.getTraderId());
        }
        Comment comment = commentsRepository.save(commentMapper.mapToComment(commentDto));
        commentDto = commentMapper.mapToDto(comment);
        return commentDto;
    }

    @Override
    public void setApprovedById(Integer id, boolean isApproved) {
        CommentDto commentDto = getById(id);
        commentDto.setApproved(isApproved);
        commentsRepository.save(commentMapper.mapToComment(commentDto));
        userService.updateRatingById(commentDto.getTraderId());
    }

    @Override
    public void delete(Integer id, String authorEmail) {
        CommentDto commentDto = getById(id);
        commentsRepository.deleteById(id);
        userService.updateRatingById(commentDto.getTraderId());
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        isOwner(commentDto.getId(), commentDto);
        commentDto.setApproved(false);
        commentDto.setTraderId(getById(commentDto.getId()).getTraderId());
        commentDto.setCreatedAt(getById(commentDto.getId()).getCreatedAt());
        commentDto = commentMapper.mapToDto(commentsRepository.save(commentMapper.mapToComment(commentDto)));
        userService.updateRatingById(commentDto.getTraderId());
        return commentDto;
    }

    private void isOwner(Integer id, CommentDto commentDto) {
        boolean isOwner = getById(id).getAuthorEmail().equals(commentDto.getAuthorEmail());
        if (!isOwner) {
            throw new UserIsNotOwnerOfCommentException(commentDto.getAuthorEmail(), commentDto.getId());
        }
    }

    @Override
    public List<CommentDto> getApprovedUserComments(Integer id) {
        userService.get(id);
        return commentsRepository.getAllByTraderIdAndApprovedIsTrue(id)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto addCommentToNonExistingTrader(String traderEmail, CommentDto commentDto) {
        UserDto trader = userService.createUnknownUserByEmail(traderEmail);
        trader = registrationService.register(trader);
        commentDto.setTraderId(trader.getId());
        return commentMapper.mapToDto(commentsRepository.save(commentMapper.mapToComment(commentDto)));
    }


}

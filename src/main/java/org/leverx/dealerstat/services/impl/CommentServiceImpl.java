package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.exceptions.UserIsNotOwnerOfComment;
import org.leverx.dealerstat.mappers.CommentMapper;
import org.leverx.dealerstat.models.Comment;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.repositories.CommentsRepository;
import org.leverx.dealerstat.services.CommentService;
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

    @Override
    public List<CommentDto> getAll() {
        List<Comment> comments = new ArrayList<>();
        commentsRepository.findAll().forEach(comments::add);
        return comments.stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getById(Integer id) {
        return commentMapper.mapToDto(commentsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(Comment.class), id)));
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        if (!userService.existsById(commentDto.getTraderId())) {
            throw new EntityNotFoundException(String.valueOf(User.class), commentDto.getTraderId());
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
    public void delete(CommentDto commentDto, String authorEmail) {
        if (!userService.get(commentDto.getTraderId()).getEmail().equals(authorEmail)) {
            throw new UserIsNotOwnerOfComment(commentDto.getAuthorEmail(), commentDto.getId());
        }
        commentsRepository.deleteById(commentDto.getId());
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, String authorEmail) {
        if (!userService.get(commentDto.getTraderId()).getEmail().equals(authorEmail)) {
            throw new UserIsNotOwnerOfComment(commentDto.getAuthorEmail(), commentDto.getId());
        }
        commentDto = commentMapper.mapToDto(commentsRepository.save(commentMapper.mapToComment(commentDto)));
        return commentDto;
    }
}

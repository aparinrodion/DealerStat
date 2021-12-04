package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.mappers.CommentMapper;
import org.leverx.dealerstat.models.Comment;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.repositories.CommentsRepository;
import org.leverx.dealerstat.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        commentsRepository.findAll().forEach(comments::add);
        return comments;
    }

    @Override
    public CommentDto getById(Integer id) {
        return commentMapper.mapToDto(commentsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(Comment.class), id)));
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment comment = commentsRepository.save(commentMapper.mapToComment(commentDto));
        commentDto = commentMapper.mapToDto(comment);
        return commentDto;
    }

    @Override
    public CommentDto delete(Integer id) {
        CommentDto commentDto = getById(id);
        commentsRepository.deleteById(id);
        return commentDto;
    }
}

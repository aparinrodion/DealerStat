package org.leverx.dealerstat.mappers;

import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment mapToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setTraderId(commentDto.getTraderId());
        comment.setApproved(commentDto.isApproved());
        comment.setMessage(commentDto.getMessage());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setRating(commentDto.getRating());
        comment.setAuthorEmail(commentDto.getAuthorEmail());
        return comment;
    }

    public CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setTraderId(comment.getTraderId());
        commentDto.setApproved(comment.isApproved());
        commentDto.setMessage(comment.getMessage());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setRating(comment.getRating());
        commentDto.setAuthorEmail(comment.getAuthorEmail());
        return commentDto;
    }
}

package org.leverx.dealerstat.mappers;

import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment mapToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setTrader_id(commentDto.getTrader_id());
        comment.setApproved(commentDto.isApproved());
        comment.setMessage(commentDto.getMessage());
        comment.setCreated_at(commentDto.getCreated_at());
        comment.setRating(commentDto.getRating());
        comment.setAuthor_email(commentDto.getAuthor_email());
        return comment;
    }

    public CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setTrader_id(comment.getTrader_id());
        commentDto.setApproved(comment.isApproved());
        commentDto.setMessage(comment.getMessage());
        commentDto.setCreated_at(comment.getCreated_at());
        commentDto.setRating(comment.getRating());
        commentDto.setAuthor_email(comment.getAuthor_email());
        return commentDto;
    }
}

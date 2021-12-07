package org.leverx.dealerstat.services;

import com.sun.mail.util.LineInputStream;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.models.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll();

    CommentDto getById(Integer id);

    CommentDto save(CommentDto commentDto);

    void setApprovedById(Integer id, boolean isApproved);

    void delete(CommentDto commentDto, String authorEmail);

    CommentDto updateComment(CommentDto commentDto, String authorEmail);
}

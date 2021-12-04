package org.leverx.dealerstat.services;

import com.sun.mail.util.LineInputStream;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();

    CommentDto getById(Integer id);

    CommentDto save(CommentDto commentDto);

    CommentDto delete(Integer id);
}

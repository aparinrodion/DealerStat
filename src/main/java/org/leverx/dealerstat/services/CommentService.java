package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll();

    CommentDto getById(Integer id);

    CommentDto save(CommentDto commentDto);

    void setApprovedById(Integer id, boolean isApproved);

    void delete(Integer id, String authorEmail);

    CommentDto updateComment(CommentDto commentDto);

    List<CommentDto> getApprovedUserComments(Integer id);

    CommentDto addCommentToNonExistingTrader(String traderEmail, CommentDto commentDto);

}

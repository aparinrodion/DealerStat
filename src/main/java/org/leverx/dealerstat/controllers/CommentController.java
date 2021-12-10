package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.services.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/users/{id}/comments")
    public List<CommentDto> getTraderComments(@PathVariable Integer id) {
        return commentService.getApprovedUserComments(id);
    }

    @GetMapping("/comments/admin")
    public List<CommentDto> getAllComments() {
        return commentService.getAll();
    }

    @PostMapping("/users/{id}/comments")
    public void addComment(@PathVariable Integer id, @Valid @RequestBody CommentDto commentDto) {
        commentDto.setTraderId(id);
        commentService.save(commentDto);
    }

    @PostMapping("/users/comments")
    public CommentDto addCommentWithoutTrader(@RequestBody @Valid CommentDto commentDto,
                                              @RequestParam String traderEmail) {
        return commentService.addCommentToNonExistingTrader(traderEmail, commentDto);
    }

    @PostMapping("/comments/{id}/approve")
    public void approveComment(@PathVariable Integer id) {
        commentService.setApprovedById(id, true);
    }

    @GetMapping("/comments/{id}")
    public CommentDto getComment(@PathVariable Integer id) {
        return commentService.getById(id);
    }

    @PutMapping("/comments/{id}")
    public void updateComment(@PathVariable Integer id,
                              @RequestBody CommentDto commentDto) {
        commentDto.setId(id);
        commentService.updateComment(commentDto);
    }

    @DeleteMapping("/comments/{id}")
    public void deletesComment(@PathVariable Integer id, @RequestParam String email) {
        commentService.delete(id, email);
    }
}

package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.models.Comment;
import org.leverx.dealerstat.services.CommentService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/users/{traderId}/comments")
    public Set<Comment> getTraderComments(@PathVariable Integer traderId) {
        UserDto userDto = userService.get(traderId);
        return userDto.getComments();
    }

    @PostMapping("/users/{id}/comments")
    public void addComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        commentDto.setTrader_id(id);
        commentService.save(commentDto);
    }

    @GetMapping("/comments/{id}")
    public CommentDto getComment(@PathVariable Integer id) {
        return commentService.getById(id);
    }

    @PutMapping("/comments/{id}")
    public void updateComment(@PathVariable Integer id, @RequestParam CommentDto commentDto) {
        commentDto.setId(id);
        commentService.save(commentDto);
    }


}

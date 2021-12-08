package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.CommentDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.mappers.CommentMapper;
import org.leverx.dealerstat.models.Comment;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.services.CommentService;
import org.leverx.dealerstat.services.RegistrationService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/users/{traderId}/comments")
    public List<CommentDto> getTraderComments(@PathVariable Integer traderId) {
        UserDto userDto = userService.get(traderId);
        return userDto.getComments()
                .stream().filter(Comment::isApproved)
                .map(commentMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping("/comments/admin")
    public List<CommentDto> getAllComments() {
        return commentService.getAll();
    }

    @PostMapping("/users/{id}/comments")
    public void addComment(@PathVariable Integer id, @RequestBody CommentDto commentDto,
                           @RequestParam String authorEmail) {
        commentDto.setTraderId(id);
        commentDto.setAuthorEmail(authorEmail);
        commentService.save(commentDto);
    }

    @PostMapping("/users/comments")
    public void addCommentWithoutTrader(@RequestBody CommentDto commentDto,
                                        @RequestParam String authorEmail,
                                        @RequestParam String traderEmail) {
        UserDto trader = userService.createUnknownUserByEmail(traderEmail);
        trader = registrationService.register(trader);
        commentDto.setAuthorEmail(authorEmail);
        commentDto.setTraderId(trader.getId());
        commentService.save(commentDto);
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
    public void updateComment(@PathVariable Integer id, @RequestParam String email, @RequestParam CommentDto commentDto) {
        commentDto.setId(id);
        commentDto.setAuthorEmail(email);
        commentService.updateComment(commentDto, email);
    }


    @DeleteMapping("/comments/{id}")
    public void deletesComment(@PathVariable Integer id, @RequestParam String email, @RequestParam CommentDto commentDto) {
        commentDto.setId(id);
        commentDto.setAuthorEmail(email);
        commentService.delete(commentDto, email);
    }
}

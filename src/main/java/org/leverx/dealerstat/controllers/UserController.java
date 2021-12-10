package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final String SKIP = "0";
    private static final String LIMIT = "10";
    private static final String RATING = "rating";

    @GetMapping
    public List<UserDto> getApprovedUsers(
            @RequestParam(defaultValue = SKIP) Integer skip,
            @RequestParam(defaultValue = LIMIT) Integer limit) {
        Pageable pageable = PageRequest.of(skip, limit,
                Sort.by(Sort.Direction.DESC, RATING));
        return userService.getApprovedUsers(pageable);
    }

    @GetMapping("/admin")
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userService.get(id);
    }

}

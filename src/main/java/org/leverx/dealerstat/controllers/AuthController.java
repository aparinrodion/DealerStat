package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.mappers.UserMapper;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<User> register() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userMapper.mapToDto(userService.get(id));
    }

    @PostMapping
    public void register(@RequestBody UserDto userDto) {

    }

}

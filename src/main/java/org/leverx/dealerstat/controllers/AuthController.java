package org.leverx.dealerstat.controllers;

import org.leverx.dealerstat.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public class AuthController {
    @PostMapping
    public void register(@RequestBody UserDto userDto){

    }

}

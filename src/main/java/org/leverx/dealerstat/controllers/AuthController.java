package org.leverx.dealerstat.controllers;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.LoginDto;
import org.leverx.dealerstat.dto.NewPasswordDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.security.JwtProvider;
import org.leverx.dealerstat.services.LoginService;
import org.leverx.dealerstat.services.NewPasswordService;
import org.leverx.dealerstat.services.RegistrationService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final RegistrationService registrationService;
    private final NewPasswordService newPasswordService;
    private final LoginService loginService;
    private final JwtProvider jwtProvider;


    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        loginService.authorize(loginDto);
        return jwtProvider.generateToken(loginDto.getEmail());
    }

    @PostMapping("/{id}/approve")
    public void approve(@PathVariable Integer id) {
        userService.setApprovedById(id, true);
    }

    @PostMapping
    public UserDto register(@RequestBody UserDto userDto) {
        return registrationService.register(userDto);
    }

    @PostMapping("/confirm/{hash_code}")
    public void confirm(@PathVariable Integer hash_code) {
        registrationService.confirm(hash_code);
    }

    @GetMapping("/check_code")
    public boolean checkCode(@RequestParam Integer code) {
        return newPasswordService.checkCode(code);
    }


    @PostMapping("/forgot_password")
    public void forgotPassword(@RequestParam String email) {
        newPasswordService.forgotPassword(email);
    }

    @PostMapping("/reset")
    public void resetPassword(@RequestBody NewPasswordDto newPasswordDto) {
        newPasswordService.resetPassword(newPasswordDto);
    }

}

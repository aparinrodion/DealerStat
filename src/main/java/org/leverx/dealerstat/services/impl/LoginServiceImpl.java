package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.LoginDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.WrongPasswordException;
import org.leverx.dealerstat.services.LoginService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void authorize(LoginDto loginDto) {
        UserDto userDto = userService.getByEmail(loginDto.getEmail());
        if (!passwordEncoder.matches(loginDto.getPassword(), userDto.getPassword())) {
            throw new WrongPasswordException(loginDto.getEmail());
        }
    }
}

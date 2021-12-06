package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.NewPasswordDto;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.RedisAlreadyHasKeyException;
import org.leverx.dealerstat.exceptions.RedisDoesntHaveKeyException;
import org.leverx.dealerstat.redis.RedisService;
import org.leverx.dealerstat.services.MailSenderService;
import org.leverx.dealerstat.services.NewPasswordService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class NewPasswordServiceImpl implements NewPasswordService {
    private final MailSenderService mailSenderService;
    private final UserService userService;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;
    private static final Integer TIME = 24;
    private static final TimeUnit TIME_UNIT = TimeUnit.HOURS;
    private final static String RESETTING_PASSWORD_SUBJECT = "Resetting password";
    private static final String RESETTING_PASSWORD_TEXT_PATTERN =
            "Hello, %s! Here's code to confirm resetting password: %d";

    @Override
    public void forgotPassword(String email) {
        UserDto userDto = userService.getByEmail(email);
        Integer code = userDto.hashCode();
        putToRedisIfNotContains(code, userDto.getId());
        sendCodeMessage(userDto, RESETTING_PASSWORD_SUBJECT, code);
    }

    @Override
    public void resetPassword(NewPasswordDto newPasswordDto) {
        Integer id = getIdIfCodeExists(newPasswordDto.getCode());
        userService.setPasswordById(id, encodePassword(newPasswordDto.getPassword()));
        redisService.delete(newPasswordDto.getCode());
    }

    @Override
    public boolean checkCode(Object code) {
        return redisService.contains(code);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void sendCodeMessage(UserDto userDto, String subject, Integer code) {
        String message = String.format(RESETTING_PASSWORD_TEXT_PATTERN,
                userDto.getFirst_name(), code);
        mailSenderService.sendMessage(userDto, RESETTING_PASSWORD_SUBJECT, message);
    }

    private void putToRedisIfNotContains(Integer code, Integer id) {
        if (redisService.contains(code)) {
            throw new RedisAlreadyHasKeyException();
        } else {
            redisService.save(code, id, TIME, TIME_UNIT);
        }
    }

    private Integer getIdIfCodeExists(String code) {
        if (!redisService.contains(code)) {
            throw new RedisDoesntHaveKeyException(String.valueOf(code));
        } else {
            return Integer.parseInt(String.valueOf(redisService.findByKey(code)));
        }
    }


}

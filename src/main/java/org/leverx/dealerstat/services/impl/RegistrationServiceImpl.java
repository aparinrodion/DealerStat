package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.UserWithEmailAlreadyExistsException;
import org.leverx.dealerstat.mappers.UserMapper;
import org.leverx.dealerstat.model.Role;
import org.leverx.dealerstat.model.User;
import org.leverx.dealerstat.redis.RedisService;
import org.leverx.dealerstat.services.MailSenderService;
import org.leverx.dealerstat.services.RegistrationService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private static final Integer TIME = 24;
    private static final TimeUnit TIME_UNIT = TimeUnit.HOURS;
    private static final String CONFIRMATION_SUBJECT = " DealerStat account confirmation";
    private static final String CONFIRMATION_LINK_PATTERN = "http://localhost:8080/auth/confirm/%d";
    private static final String CONFIRMATION_TEXT_PATTERN = "Hello, %s! Follow link to confirm account\n";

    private final RedisService redisService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserDto userDto) {
        User user = createUserIfNotExist(userDto);
        user = userMapper.mapToUser(userService.save(userMapper.mapToDto(user)));
        redisService.save(user.hashCode(), user.getId(), TIME, TIME_UNIT);
        sendConfirmMessage(userMapper.mapToDto(user), user.hashCode());
        return userMapper.mapToDto(user);
    }

    @Override
    public void confirm(Integer hashCode) {
        Integer id = Integer.parseInt(String.valueOf(redisService.findByKey(hashCode)));
        redisService.delete(hashCode);
        userService.setConfirmedById(id, true);
    }

    private void sendConfirmMessage(UserDto userDto, Integer hashCode) {
        String message = String.format(CONFIRMATION_TEXT_PATTERN +
                CONFIRMATION_LINK_PATTERN, userDto.getFirstName(), hashCode);
        mailSenderService.sendMessage(userDto, CONFIRMATION_SUBJECT, message);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private User createUserIfNotExist(UserDto userDto) {
        if (userService.existsByEmail(userDto.getEmail())) {
            throw new UserWithEmailAlreadyExistsException(userDto.getEmail());
        }
        User user = userMapper.mapToUser(userDto);
        user.setPassword(encodePassword(userDto.getPassword()));
        user.setRoles(Collections.singleton(Role.TRADER));
        return user;
    }
}

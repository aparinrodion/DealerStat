package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.mailsender.MailSender;
import org.leverx.dealerstat.mappers.UserMapper;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.repositories.UserRepository;
import org.leverx.dealerstat.services.MailSenderService;
import org.leverx.dealerstat.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final UserMapper userMapper;

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User get(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public void save(UserDto userDto) {
        userRepository.save(userMapper.mapToUser(userDto));
    }


}

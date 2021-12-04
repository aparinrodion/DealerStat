package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.exceptions.UserWithEmailNotFoundException;
import org.leverx.dealerstat.mappers.UserMapper;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.repositories.UserRepository;
import org.leverx.dealerstat.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public UserDto get(Integer id) {
        return userMapper.mapToDto(userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.valueOf(User.class), id)));
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        user = userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void setConfirmedById(Integer id, boolean isActivated) {
        UserDto userDto = get(id);
        userDto.setConfirmed(true);
        save(userDto);
    }

    @Override
    public void setPasswordById(Integer id, String password) {
        UserDto userDto = get(id);
        userDto.setPassword(password);
        save(userDto);
    }

    @Override
    public boolean isEmailConfirmedByEmail(String email) {
        return getByEmail(email).isConfirmed();
    }

    @Override
    public UserDto getByEmail(String email) {
        if (!existsByEmail(email)) {
            throw new UserWithEmailNotFoundException(email);
        }
        return userMapper.mapToDto(userRepository.findByEmail(email));
    }


}

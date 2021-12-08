package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.EntityNotFoundException;
import org.leverx.dealerstat.exceptions.UserWithEmailAlreadyExistsException;
import org.leverx.dealerstat.exceptions.UserWithEmailNotFoundException;
import org.leverx.dealerstat.mappers.UserMapper;
import org.leverx.dealerstat.models.GameObject;
import org.leverx.dealerstat.models.User;
import org.leverx.dealerstat.repositories.UserRepository;
import org.leverx.dealerstat.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final String UNKNOWN_FIRST_NAME = "unknown_first_name";
    private static final String UNKNOWN_LAST_NAME = "unknown_last_name";
    private static final String UNKNOWN_PASSWORD = "t;$q)UC8{UMd>a}]";

    @Override
    public List<UserDto> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users.stream().map(userMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getApprovedUsers(Pageable pageable) {
        List<User> users = new ArrayList<>(userRepository.getAllByApproved(true, pageable));
        return users.stream().map(userMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto get(Integer id) {
        return userMapper.mapToDto(userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.valueOf(User.class), id)));
    }

    @Override
    public UserDto save(UserDto userDto) {
        if (!existsByEmail(userDto.getEmail())) {
            User user = userMapper.mapToUser(userDto);
            user = userRepository.save(user);
            return userMapper.mapToDto(user);
        } else throw new UserWithEmailAlreadyExistsException(userDto.getEmail());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void setConfirmedById(Integer id, boolean isActivated) {
        UserDto userDto = get(id);
        userDto.setConfirmed(isActivated);
        save(userDto);
    }

    @Override
    public void setApprovedById(Integer id, boolean isApproved) {
        UserDto userDto = get(id);
        if (userDto.isApproved() != isApproved) {
            userDto.setApproved(isApproved);
            save(userDto);
        }
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

    @Override
    public List<GameObject> getPrincipalGameObjects(Principal principal) {
        UserDto userDto = getByEmail(principal.getName());
        return new ArrayList<>(userDto.getGameObjects());
    }

    @Override
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDto createUnknownUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            UserDto userDto = new UserDto();
            userDto.setFirstName(UNKNOWN_FIRST_NAME);
            userDto.setLastName(UNKNOWN_LAST_NAME);
            userDto.setPassword(UNKNOWN_PASSWORD);
            userDto.setEmail(email);
            return userDto;
        } else throw new UserWithEmailAlreadyExistsException(email);

    }

    @Override
    public void updateRatingById(Integer id) {
        UserDto userDto = get(id);
        userDto.countRating();
        userRepository.save(userMapper.mapToUser(userDto));
    }

}

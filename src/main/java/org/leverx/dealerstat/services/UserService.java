package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    UserDto get(Integer id);

    UserDto save(UserDto userDto);

    boolean existsByEmail(String email);

    void setConfirmedById(Integer id, boolean isActivated);

    void setPasswordById(Integer id, String password);

    boolean isEmailConfirmedByEmail(String email);

    UserDto getByEmail(String email);

}

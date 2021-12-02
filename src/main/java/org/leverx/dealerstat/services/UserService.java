package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();

    User get(Integer id);

    void save(UserDto userDto);


}

package org.leverx.dealerstat.mappers;

import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setCreated_at(userDto.getCreated_at());
        user.setConfirmed(userDto.isConfirmed());
        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirst_name(user.getFirst_name());
        userDto.setLast_name(user.getLast_name());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setCreated_at(user.getCreated_at());
        userDto.setConfirmed(user.isConfirmed());
        return userDto;

    }
}

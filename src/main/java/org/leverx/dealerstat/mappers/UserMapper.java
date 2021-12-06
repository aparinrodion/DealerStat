package org.leverx.dealerstat.mappers;

import lombok.NoArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.models.User;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserMapper {
    public User mapToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setConfirmed(userDto.isConfirmed());
        user.setRoles(userDto.getRoles());
        user.setComments(userDto.getComments());
        user.setGameObjects(userDto.getGameObjects());
        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setConfirmed(user.isConfirmed());
        userDto.setRoles(user.getRoles());
        userDto.setComments(user.getComments());
        userDto.setGameObjects(user.getGameObjects());
        return userDto;

    }
}

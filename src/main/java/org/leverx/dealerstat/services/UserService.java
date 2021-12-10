package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.model.GameObject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface UserService {
    List<UserDto> getUsers();

    List<UserDto> getApprovedUsers(Pageable pageable);

    UserDto get(Integer id);

    UserDto save(UserDto userDto);

    boolean existsByEmail(String email);

    void setConfirmedById(Integer id, boolean isActivated);

    void setApprovedById(Integer id, boolean isActivated);

    void setPasswordById(Integer id, String password);

    UserDto getByEmail(String email);

    List<GameObject> getGameObjectsByPrincipal(Principal principal);

    boolean existsById(Integer id);

    UserDto createUnknownUserByEmail(String email);

    void updateRatingById(Integer id);
}

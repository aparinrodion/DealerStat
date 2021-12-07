package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    UserDto register(UserDto userDto);

    void confirm(Integer hashCode);
}

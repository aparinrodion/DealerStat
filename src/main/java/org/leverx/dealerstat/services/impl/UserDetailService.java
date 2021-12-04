package org.leverx.dealerstat.services.impl;


import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.exceptions.EmailNotConfirmedException;
import org.leverx.dealerstat.models.Role;
import org.leverx.dealerstat.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDto userDto = getUserDtoIfEmailConfirmedElseThrowException(email);
        return new User(userDto.getEmail(), userDto.getPassword(), mapRolesToAuthorities(userDto.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    private UserDto getUserDtoIfEmailConfirmedElseThrowException(String email) {
        if (!userService.isEmailConfirmedByEmail(email)) {
            throw new EmailNotConfirmedException(email);
        } else {
            return userService.getByEmail(email);
        }
    }
}

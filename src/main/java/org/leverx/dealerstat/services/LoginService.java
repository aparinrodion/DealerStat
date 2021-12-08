package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.LoginDto;

public interface LoginService {
    void authorize(LoginDto loginDto);
}

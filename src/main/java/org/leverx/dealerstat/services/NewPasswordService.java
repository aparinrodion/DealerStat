package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.NewPasswordDto;

public interface NewPasswordService {
    void forgotPassword(String email);

    void resetPassword(NewPasswordDto newPasswordDto);
}

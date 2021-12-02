package org.leverx.dealerstat.services;

import org.leverx.dealerstat.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface MailSenderService {
    void sendMessage(UserDto userDto, String subject, String message);
}

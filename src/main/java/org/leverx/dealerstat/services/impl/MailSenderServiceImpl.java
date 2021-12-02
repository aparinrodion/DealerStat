package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.mailsender.MailSender;
import org.leverx.dealerstat.services.MailSenderService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final MailSender mailSender;
    private final String CONFIRMATION_LINK_PATTERN = "http://localhost:8080/students/auth/%s";

    @Override
    public void sendMessage(UserDto userDto, String subject, String text) {
        String message = String.format("Hello, %s! Follow link to confirm account\n" +
                CONFIRMATION_LINK_PATTERN, userDto.getFirst_name(), text);
        mailSender.sendMessage(userDto.getEmail(), subject, message);
    }
}

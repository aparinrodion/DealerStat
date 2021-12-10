package org.leverx.dealerstat.services.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.dto.UserDto;
import org.leverx.dealerstat.mail.MailSender;
import org.leverx.dealerstat.services.MailSenderService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final MailSender mailSender;

    @Override
    public void sendMessage(UserDto userDto, String subject, String text) {
        mailSender.sendMessage(userDto.getEmail(), subject, text);
    }
}

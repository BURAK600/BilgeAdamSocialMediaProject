package com.burak.service;

import com.burak.dto.request.ActivatedRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;

    public void sendActivateCode(ActivatedRequestDto dto){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("burakozer539@gmail.com");

        mailMessage.setTo(dto.getEmail());
        mailMessage.setSubject("Activation Kodunuz");
        mailMessage.setText(dto.getActivatedCode());
        mailSender.send(mailMessage);
    }
}

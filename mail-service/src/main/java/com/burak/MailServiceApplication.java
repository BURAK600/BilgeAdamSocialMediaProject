package com.burak;

import com.burak.dto.request.ActivatedRequestDto;
import com.burak.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MailServiceApplication {

//    @Autowired
//    EmailSenderService service;


    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class,args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        ActivatedRequestDto dto = ActivatedRequestDto.builder()
//                .email("burakozer539@gmail.com")
//                .activatedCode("xx-yyy")
//                .build();
//        service.sendActivateCode(dto);
//    }

}
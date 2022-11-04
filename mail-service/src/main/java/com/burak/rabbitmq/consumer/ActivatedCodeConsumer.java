package com.burak.rabbitmq.consumer;


import com.burak.dto.request.ActivatedRequestDto;
import com.burak.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivatedCodeConsumer {
    private final EmailSenderService emailSenderService;

    @RabbitListener(queues = "queueActivatedCode")

    public void activatedMessage(ActivatedRequestDto dto){

        log.info("Activate: {}", dto.toString());
        emailSenderService.sendActivateCode(dto);
    }
}

package com.burak.rabbitmq.producer;

import com.burak.rabbitmq.model.ActivatedRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivatedCodeProcedure {

    private final RabbitTemplate rabbitTemplate;
    public void sendActivationCode(ActivatedRequestDto dto){
        rabbitTemplate.convertAndSend("authDirectExchange", "bindingKeyActivatedCode", ActivatedRequestDto.builder()

                .build()

        );
    }
}

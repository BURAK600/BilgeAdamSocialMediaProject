package com.burak.rabbitmq.producer;

import com.burak.rabbitmq.model.UpdateUserNameEmailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UpdateUserProcedure {
    @Value("${rabbitmq.exchangeuser}")
    private String userDirectExchange;
    @Value("${rabbitmq.userUpdateQueue}")
    private String userUpdateQueue;
    @Value("${rabbitmq.userUpdateBinding}")
    private String userUpdateBinding;
    private final RabbitTemplate rabbitTemplate;

    public void sendUpdateUser(UpdateUserNameEmailModel model){

        rabbitTemplate.convertAndSend(userDirectExchange, userUpdateBinding, model);

    }

}

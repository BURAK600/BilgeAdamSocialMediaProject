package com.burak.rabbitmq.consumer;


import com.burak.rabbitmq.model.UpdateUserNameEmailModel;
import com.burak.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserConsumer {


private final AuthService authService;

    @RabbitListener(queues = "${rabbitmq.userUpdateQueue}")

    public void updateUser(UpdateUserNameEmailModel model){
        log.info("User: {}", model.toString());
        authService.updateAuth(model);


    }



}

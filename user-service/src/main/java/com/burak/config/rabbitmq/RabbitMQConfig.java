package com.burak.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
@Value("${rabbitmq.exchangeuser}")
    private String userDirectExchange;
@Value("${rabbitmq.userUpdateQueue}")
    private String userUpdateQueue;
@Value("${rabbitmq.userUpdateBinding}")
    private String userUpdateBinding;


    @Bean
    DirectExchange exchangeUser(){
        return new DirectExchange(userDirectExchange);
    }

    @Bean
    Queue userUpdateQueue(){
        return new Queue(userUpdateQueue);
    }


    @Bean
    public Binding bindingUpdateUser(final Queue userUpdateQueue, final DirectExchange exchangeUser){
        /**
         * Exchange ile Queue arasında binding işlemi yapılır
         * su queue ile bu exchange i bu anahtar ile bağla
         */
        return BindingBuilder.bind(userUpdateQueue).to(exchangeUser).with(userUpdateBinding);
    }

}

package com.burak.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private String authDirectExchange = "exchange";
    private String queueActivatedCode = "queueNameActivatedCode";
    private String bindingKeyActivatedCode = "bindingKeyActivatedCode";

    @Bean
    DirectExchange authDirectExchange(){
        return new DirectExchange(authDirectExchange);
    }

    @Bean
    Queue queueCreateUser(){
        return new Queue(queueActivatedCode);
    }


    @Bean
    public Binding bindingCreateUser(final Queue queueCreateUser, final DirectExchange authDirectExchange){
        /**
         * Exchange ile Queue arasında binding işlemi yapılır
         * su queue ile bu exchange i bu anahtar ile bağla
         */
        return BindingBuilder.bind(queueCreateUser).to(authDirectExchange).with(bindingKeyActivatedCode);
    }

}

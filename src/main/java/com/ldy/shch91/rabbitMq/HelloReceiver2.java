package com.ldy.shch91.rabbitMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
@Slf4j
public class HelloReceiver2 {

    @RabbitHandler
    public void process(String hello) {
        log.info("Receiver2  : " + hello);
    }

}
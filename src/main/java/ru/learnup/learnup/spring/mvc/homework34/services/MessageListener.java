package ru.learnup.learnup.spring.mvc.homework34.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageListener {

    @JmsListener(destination = "order-mq")
    public void processOrderMq(@Payload MessageBody messageBody) {
        log.info("Date received: {}", messageBody);
//        System.out.println("Current Date " + messageBody);
    }

//    @JmsListener(destination = "order-mq-text")
//    public void processMikeMqText(String message) {
//        log.info("Date received: {}", message);
//    }
}

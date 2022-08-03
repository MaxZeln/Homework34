package ru.learnup.learnup.spring.mvc.homework34.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageSender {

    private final JmsTemplate jmsTemplate;
    private final String orderMq;
    private Date date;

    public MessageSender(JmsTemplate jmsTemplate,
                         @Value("${order.mq}") String orderMq) {
        this.jmsTemplate = jmsTemplate;
        this.orderMq = orderMq;

    }

    public void sendMessage(String msg) {
        date = new Date();
        String dateS = date.toString();
        jmsTemplate.convertAndSend(orderMq, new MessageBody("Current date", dateS));
    }
}

package com.ManageUsers.ManageUsers.Messaging;

import com.ManageUsers.ManageUsers.Config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendEmail(EmailServiceImpl emailMessage) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, emailMessage);
    }
}

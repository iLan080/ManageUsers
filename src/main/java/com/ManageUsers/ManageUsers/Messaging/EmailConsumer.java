package com.ManageUsers.ManageUsers.Messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    //@RabbitListener(queues = "emailQueue")
    public void sendEmail(EmailMessage emailMessage) {

        String to = emailMessage.getTo();
        String subject = emailMessage.getSubject();
        String body = emailMessage.getBody();

        // emailService.sendEmailMessage(to, subject, body);
    }
}

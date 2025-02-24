
package com.ManageUsers.ManageUsers.Messaging;


import com.ManageUsers.ManageUsers.Config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.ManageUsers.ManageUsers.Messaging.EmailMessage;


@Component
public class EmailMessageListener {

    private final EmailService emailService;


    public EmailMessageListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(EmailMessage emailMessage) {


        System.out.println("Received email message:");
        System.out.println("To: " + emailMessage.getTo());
        System.out.println("Subject: " + emailMessage.getSubject());
        System.out.println("Body: " + emailMessage.getBody());


        emailService.sendEmailMessage(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getBody());

    }
}
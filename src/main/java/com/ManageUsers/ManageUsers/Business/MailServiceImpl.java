package com.ManageUsers.ManageUsers.Business;

//import com.ManageUsers.ManageUsers.Config.RabbitMQConfig;
import com.ManageUsers.ManageUsers.Config.RabbitMQConfig;
import com.ManageUsers.ManageUsers.DataAccess.IUserDal;
import com.ManageUsers.ManageUsers.Messaging.EmailMessage;
//import com.ManageUsers.ManageUsers.Messaging.EmailProducer;
//import com.ManageUsers.ManageUsers.Messaging.EmailService;
import com.ManageUsers.ManageUsers.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MailServiceImpl implements MailService {


    private JavaMailSender mailSender;

//    @Autowired
//    private EmailProducer emailProducer;




    @Qualifier("hibernateUserDal")
    @Autowired
    private IUserDal userDal;


    private ScheduledExecutorService scheduler;
    private final RabbitTemplate rabbitTemplate;

    private List<User> usersToEmail;
    private Iterator<User> userIterator;

    @Autowired
    public MailServiceImpl(RabbitTemplate rabbitTemplate, @Qualifier("hibernateUserDal") IUserDal userDal, JavaMailSender mailSender) {
        this.rabbitTemplate = rabbitTemplate;
        this.userDal = userDal;
        this.mailSender = mailSender;
    }


    public List<User> get100UsersById() {
        List<User> userList = this.userDal.getAll();
        return userList.stream()
                .sorted(Comparator.comparing(User::getUserId))
                .limit(4)
                .collect(Collectors.toList());
    }


    public void sendEmailsToTop100Users() {
        List<User> usersToEmail = get100UsersById();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduleNextEmails(usersToEmail, scheduler);
    }

    private void scheduleNextEmails(List<User> usersToEmail, ScheduledExecutorService scheduler) {
        if (!usersToEmail.isEmpty()) {
            scheduler.schedule(() -> {
                User user = usersToEmail.remove(0);
                sendEmailMessage(user.getEmail(), "123", "3456");
                scheduleNextEmails(usersToEmail, scheduler);
            }, 1, TimeUnit.MINUTES);
        } else {
            scheduler.shutdown();
        }
    }

    private void sendEmailMessage(String email, String subject, String body) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject(subject);
        emailMessage.setBody(body);

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, emailMessage);
    }
}
    //------!!!!!!!!!!

   // @Override
   // @Transactional
   // public List<User> get100UsersById() {
    //  List<User> userList = this.userDal.getAll();

    // return userList.stream()
    //   .sorted(Comparator.comparing(User::getUserId))
    //   .limit(100)
    //   .collect(Collectors.toList());
    // }

    // @Override
    //  @Transactional
    // public void sendEmailsToTop100Users() {
    // usersToEmail = get100UsersById();
    // currentIndex = 0; // Reset the index

    //  if (scheduler != null && !scheduler.isShutdown()) {
    //      scheduler.shutdown(); // Eski scheduler'ı kapat
    //  }

    //  scheduler = Executors.newScheduledThreadPool(1);
    //  sendInitialEmail(); // İlk e-postayı hemen gönder

    //  scheduler.scheduleAtFixedRate(this::sendEmails, 1, 1, TimeUnit.MINUTES); // 1 dakika aralıklarla çalıştır
    // }

    //  private void sendInitialEmail() {
    //    if (usersToEmail != null && currentIndex < usersToEmail.size()) {
    //       User user = usersToEmail.get(currentIndex);
    //      emailService.sendEmailMessage(user.getEmail(), "Subject", "Message body");
    //       currentIndex++;
    //   }
    // }

    //public void sendEmails() {
    //   if (usersToEmail != null && currentIndex < usersToEmail.size()) {
            //     User user = usersToEmail.get(currentIndex);
            //     emailService.sendEmailMessage(user.getEmail(), "Subject", "Message body");
            //    currentIndex++;
            //   } else {
            //     scheduler.shutdown(); // Tüm e-postalar gönderildikten sonra scheduler'ı kapat
            //   }
        // }



    //-----

   // @Override
   //@Transactional
    //public List<User> get100UsersById() {
       //  List<User> userList = this.userDal.getAll();

        //return userList.stream()
        //     .sorted(Comparator.comparing(User::getUserId))
        //    .limit(2)
        //    .collect(Collectors.toList());
        //}

    // @Override
    // @Transactional
    // public void sendEmailsToTop100Users() {
        //  usersToEmail = get100UsersById();
        //  currentIndex = 0; // Reset the index
        //  sendEmails();
        //  }

    // @Scheduled(fixedDelay = 30000) // 1 dakika aralıklarla çalıştır
    //public void sendEmails() {
        // if (usersToEmail != null && currentIndex < usersToEmail.size()) {
            // User user = usersToEmail.get(currentIndex);
            //emailProducer.sendEmailToQueue(user);
            // //sendEmail(user.getEmail(), "!!!", "SENT");
            //  currentIndex++;
            // }
        // }

   // private void sendEmail(String to, String subject, String text) {
    //   SimpleMailMessage message = new SimpleMailMessage();
    //   message.setFrom("xilhanunx@gmail.com");
    //   message.setTo(to);
    //  message.setSubject(subject);
    //   message.setText(text);
    //  mailSender.send(message);

    //}






    //-----------

    // @Override
    // @Transactional
    //  public List<User> get100EmailSentMail() {
    //   List<User> userList = this.userDal.getAll();

    //  List<String> sortedUserMailList = userList.stream()
    //         .map(User::getEmail)
    //         .collect(Collectors.toList());

    //  SimpleMailMessage message = new SimpleMailMessage();
    // message.setFrom("norply@metsoft.com");
    //  message.setTo(String.valueOf(sortedUserMailList));
    //  message.setText("Your email has been sent");
    //  message.setSubject("!!SET!!");
    //   mailSender.send(message);

    //  return null;
    // }




    //@Override
    // public String sendMail() {
    //    SimpleMailMessage message = new SimpleMailMessage();
    //     message.setFrom("norply@metsoft.com");
    //     message.setTo("ilhan.unn@gmail.com");
    //    message.setText("Your email has been sent to Deneme.com");
    //    message.setSubject("!!SET!!");
    //    mailSender.send(message);
    //     return "sent";
    // }







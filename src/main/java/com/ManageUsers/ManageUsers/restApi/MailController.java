package com.ManageUsers.ManageUsers.restApi;


//import com.ManageUsers.ManageUsers.Business.MailServiceImpl;
//import com.ManageUsers.ManageUsers.Messaging.EmailService;
import com.ManageUsers.ManageUsers.Business.MailServiceImpl;
import com.ManageUsers.ManageUsers.Messaging.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class MailController {

//    private final MailService mailService;
   private final EmailService emailService;
   private final MailServiceImpl mailService;

    @Autowired
    public MailController(MailServiceImpl mailService, EmailService emailService) {
        this.mailService = mailService;
        this.emailService = emailService;
    }

//    @Autowired
//    public MailController(  IEmailService emailService) {
//
//        this.emailService = emailService;
//    }


    //@Autowired
   // public MailController(MailService mailService) {
       // this.mailService = mailService;
    //}

   // @GetMapping("/yolla")
    //public ResponseEntity<String> sendEmail() {

        //return ResponseEntity.ok(mailService.sendMail());
   // }

    //------r
    @PostMapping("/sendEmails")
    public String sendEmailsToTop100Users() {
    mailService.sendEmailsToTop100Users();
    return "E-posta gönderimi başlatıldı.";
    }
//Rrrrrr

//    @PostMapping("/sendEmails")
//    public ResponseEntity<Void> sendEmailsToTop100Users() {
//        emailService.sendEmailsToTop100Users();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//----------
    // @PostMapping("/send")
    // public ResponseEntity<String> sendEmail(@RequestBody EmailMessage emailRequest) {
        //try {
            // emailService.sendEmailMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
            //  return ResponseEntity.ok("Email message sent successfully.");
            //} catch (Exception e) {
            // e.printStackTrace();
            //  return ResponseEntity.status(500).body("Failed to send email message.");
       // }
    //}
}

package com.ManageUsers.ManageUsers.Business;

import com.ManageUsers.ManageUsers.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MailService {






//    @Transactional
//        List<User> get100EmailSentMail();

    // sendMail();

    List<User> get100UsersById();

    void sendEmailsToTop100Users();

//    void initializeUserIterator();
}

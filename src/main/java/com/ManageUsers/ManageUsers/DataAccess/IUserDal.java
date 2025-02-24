package com.ManageUsers.ManageUsers.DataAccess;

import com.ManageUsers.ManageUsers.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDal {

    List<User> getAll();
    void add(User user);
    void update(User user);
    void delete(User user);
    User getUserById(int userId);
    List<User> get100Name();
    List<User> getAllUserNames();
    void updateUserStatus(int userId, boolean status);
}


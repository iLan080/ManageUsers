package com.ManageUsers.ManageUsers.Business;

import com.ManageUsers.ManageUsers.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUserService {

    List<User> getAll();
    void add(User user);
    void update(User user);
    void delete(User user);
    User getUserById(int userId);
    List<User> get100Name();
    List<String> getAllUserNames();
    List<User> getUsersByNames(String... names);
    Map<String, List<User>> getDuplicateNames();
    Map<String, List<User>> getDuplicateNames1();
    Map<String, Map<Long, List<User>>> getDuplicateNames3();
    List <String> getUniqueNamesDistinct();
    Set<String> getUniqueNamesSet();
    List<String> getUniqueNamesGroupCon();
    List<String> getUniqueNamesGroup();
    List <String> getUniqueNamesDistinctP();
    Set<String> getUniqueNamesSetP();
    List<String> getUniqueNamesGroupConP();
    List<String> getUniqueNamesGroupP();
    Map<String, List<User>> getDuplicateNames1P();
    Map<String, List<User>> getDuplicateNamesP();



}

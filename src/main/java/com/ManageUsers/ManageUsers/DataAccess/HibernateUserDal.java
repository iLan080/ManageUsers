package com.ManageUsers.ManageUsers.DataAccess;

import com.ManageUsers.ManageUsers.entity.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class HibernateUserDal implements IUserDal   {


   private EntityManager entityManager;

public HibernateUserDal(EntityManager entityManager) {
    this.entityManager = entityManager;
}
@Override
    @Transactional
    public List<User> getAll() {
      Session session =  entityManager.unwrap(Session.class);

      List<User> users = session.createQuery("from User",User.class).getResultList();
      return users;
    }

    @Override

    public void add(User user) {

        Session session =  entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
    }

    @Override

    public void update(User user) {

    Session session =  entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);

    }

    @Override

    public void delete(User user) {

    Session session =  entityManager.unwrap(Session.class);
        User userToDelete =session.get(User.class, user.getUserId());

    session.delete(userToDelete);

    }

    @Override
    public User getUserById(int userId) {

    Session session =  entityManager.unwrap(Session.class);
    User user=session.get(User.class, userId);
    return user;

    }

    @Override
    public List<User> get100Name() {
    Session session =  entityManager.unwrap(Session.class);
    List<User> users=session.createQuery("from User",User.class).getResultList();
    return users;

    }

    @Override
    public List<User> getAllUserNames() {
        Session session = entityManager.unwrap(Session.class);
        List<User> users = session.createQuery("from User", User.class).getResultList();
        List<String> names = new ArrayList<>();
        for (User user : users) {
            user.getFirstname();
        }
        return users;
    }

    @Override
    @Transactional
    public void updateUserStatus(int userId, boolean status) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, userId);
        if (user != null) {
            user.setStatus(status);
            session.saveOrUpdate(user);
        }
    }

}

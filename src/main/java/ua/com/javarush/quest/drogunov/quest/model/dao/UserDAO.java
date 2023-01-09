package ua.com.javarush.quest.drogunov.quest.model.dao;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;


public class UserDAO extends BaseEntityDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }
}

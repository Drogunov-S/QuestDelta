package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;

public class UserRepository extends AbstractRepository<User>{
    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }
    
    
}

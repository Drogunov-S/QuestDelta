package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;

public class UserRepository extends AbstractRepository<User>{
    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }
    
    @Override
    public User getById(Long id) {
        getCurrentSession().beginTransaction();
        User user = super.getById(id);
        getCurrentSession().getTransaction().commit();
        return user;
    }
}

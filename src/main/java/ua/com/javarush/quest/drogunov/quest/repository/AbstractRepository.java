package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.dao.BaseEntityDAO;
import ua.com.javarush.quest.drogunov.quest.model.entity.BaseEntity;

public abstract class AbstractRepository<T extends BaseEntity> extends BaseEntityDAO<T> {
        protected AbstractRepository(SessionFactory sessionFactory, Class<T> aClass) {
                super(sessionFactory, aClass);
        }
        
}

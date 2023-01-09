package ua.com.javarush.quest.drogunov.quest.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BaseEntityDAO<T> implements CrudDb<T>{
    private final Class<T> aClass;
    private final SessionFactory sessionFactory;
    
    protected BaseEntityDAO(SessionFactory sessionFactory, Class<T> aClass) {
        this.sessionFactory = sessionFactory;
        this.aClass = aClass;
    }
    public T save(final T entity) {
        getCurrentSession().save(entity);
        return entity;
    }
    
    public T getById(Long id) {
        return getCurrentSession().get(aClass, id);
    }
    
    public List<T> getAll(int offset, int limit) {
        return getCurrentSession()
                .createQuery("from " + aClass.getName(), aClass)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }
    
    public T update(final T entity) {
        getCurrentSession().update(entity);
        return entity;
    }
    
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }
    
    public void deleteById(Long id) {
        T entity = getById(id);
        delete(entity);
    }
    
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}

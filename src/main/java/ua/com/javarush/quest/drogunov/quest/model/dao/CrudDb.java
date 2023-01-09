package ua.com.javarush.quest.drogunov.quest.model.dao;

import org.hibernate.SessionFactory;

import java.util.List;

public interface CrudDb<T> {
    
    T save(final T entity);
    T getById(Long id);
    List<T> getAll(int offset, int limit);
    T update(final T entity);
    void delete(T entity);
    void deleteById(Long id);
    
}

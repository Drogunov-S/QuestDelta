package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;

import java.util.List;

public class QuestRepository extends AbstractRepository<Quest> {
    public QuestRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Quest.class);
    }
    
    @Override
    public List<Quest> getAll(int offset, int limit) {
        Session currentSession = getCurrentSession();
        currentSession.beginTransaction();
        List<Quest> all = super.getAll(offset, limit);
        currentSession.getTransaction().commit();
        return all;
    }
    
    @Override
    public Quest getById(Long id) {
        Session currentSession = getCurrentSession();
        currentSession.beginTransaction();
        Quest quest = super.getById(id);
        currentSession.getTransaction().commit();
        return quest;
    }
}

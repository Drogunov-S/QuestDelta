package ua.com.javarush.quest.drogunov.quest.model.dao;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;

public class QuestDAO extends BaseEntityDAO<Quest> {
    public QuestDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Quest.class);
    }
}

package ua.com.javarush.quest.drogunov.quest.model.dao;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;


public class AnswerDAO extends BaseEntityDAO<Answer> {
    public AnswerDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Answer.class);
    }
}

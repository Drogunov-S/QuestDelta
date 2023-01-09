package ua.com.javarush.quest.drogunov.quest.model.dao;

import lombok.*;
import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;

import java.util.Set;

public class QuestionDAO extends BaseEntityDAO<Question> {
    public QuestionDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Question.class);
    }
}

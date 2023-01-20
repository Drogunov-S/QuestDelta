package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;

public class AnswerRepository extends AbstractRepository<Answer>{
    public AnswerRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Answer.class);
    }
}

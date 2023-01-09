package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;

public class QuestionRepository extends AbstractRepository<Question>{
    public QuestionRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Question.class);
    }
}

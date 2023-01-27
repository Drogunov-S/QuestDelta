package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.Session;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;

import java.util.List;

public class TestMappingCollections {
    public static void main(String[] args) {
        try (Session session = DbSession.getSessionFactory().openSession()) {
            session.beginTransaction();
            Quest quest = session.get(Quest.class, 1L);
            List<Question> questions = quest.getQuestions();
            System.out.println(questions);
            
            
            
            session.getTransaction().commit();
        } finally {
            DbSession.getSessionFactory().close();
        }
    }
}

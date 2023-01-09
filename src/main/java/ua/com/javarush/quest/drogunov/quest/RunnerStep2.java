package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.*;
import ua.com.javarush.quest.drogunov.quest.repository.*;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;

import java.util.List;

public class RunnerStep2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = DbSession.getSessionFactory();
        UserRepository userRepository = new UserRepository(sessionFactory);
        QuestRepository questRepository = new QuestRepository(sessionFactory);
        QuestionRepository questionRepository = new QuestionRepository(sessionFactory);
        AnswerRepository answerRepository = new AnswerRepository(sessionFactory);
        GameRepository gameRepository = new GameRepository(sessionFactory);
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        List<User> all = userRepository.getAll(0, 5);
        List<Quest> all1 = questRepository.getAll(0, 3);
        List<Question> all2 = questionRepository.getAll(0, 3);
        List<Answer> all3 = answerRepository.getAll(0, 4);
        List<Game> all4 = gameRepository.getAll(0, 2);
        currentSession.getTransaction().commit();
        System.out.println(all);
        System.out.println("////////////////".repeat(10));
        System.out.println(all1);
        System.out.println("////////////////".repeat(10));
        System.out.println(all2);
        System.out.println("////////////////".repeat(10));
        System.out.println(all3);
        System.out.println("////////////////".repeat(10));
        System.out.println(all4);
        System.out.println("////////////////".repeat(10));
    }
}

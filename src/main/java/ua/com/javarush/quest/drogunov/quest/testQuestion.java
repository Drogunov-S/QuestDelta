package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;
import ua.com.javarush.quest.drogunov.quest.repository.GameRepository;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

import java.util.List;

public class testQuestion {
    public static void main(String[] args) {
        SessionFactory sessionFactory = DbSession.getSessionFactory();
        RepositoryFactory repositoryFactory = new RepositoryFactory(sessionFactory);
        GameRepository repository = repositoryFactory.getRepository(GameRepository.class);
        sessionFactory.getCurrentSession().beginTransaction();
        Game byId = repository.getById(1L);
        sessionFactory.getCurrentSession().getTransaction().commit();
        List<Question> questions = byId.getQuest().getQuestions();
        System.out.println(questions);
    }
}

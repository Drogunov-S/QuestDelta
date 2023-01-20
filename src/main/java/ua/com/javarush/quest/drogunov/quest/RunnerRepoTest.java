package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.repository.UserRepository;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

import java.util.List;

public class RunnerRepoTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = DbSession.getSessionFactory();
        RepositoryFactory repositoryFactory = new RepositoryFactory();
        UserRepository userRepository = repositoryFactory.getRepository(UserRepository.class);
        QuestRepository questRepository = repositoryFactory.getRepository(QuestRepository.class);
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        List<User> all = userRepository.getAll(0, 4);
        System.out.println("////////////////////////////////////////////////");
        System.out.println(all);
        System.out.println("////////////////////////////////////////////////");
        List<Quest> all1 = questRepository.getAll(0, 2);
        System.out.println("////////////////////////////////////////////////");
        System.out.println(all1);
        System.out.println("////////////////////////////////////////////////");
        currentSession.getTransaction().commit();
    }
}

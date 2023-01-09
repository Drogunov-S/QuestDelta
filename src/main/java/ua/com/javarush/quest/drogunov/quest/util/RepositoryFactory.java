package ua.com.javarush.quest.drogunov.quest.util;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.repository.*;

import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {
    private final Map<Class<?>, AbstractRepository<?>> repositories;
    
    public RepositoryFactory(SessionFactory sessionFactory) {
        repositories = new HashMap<>();
        repositories.put(AnswerRepository.class, new AnswerRepository(sessionFactory));
        repositories.put(GameRepository.class, new GameRepository(sessionFactory));
        repositories.put(QuestionRepository.class, new QuestionRepository(sessionFactory));
        repositories.put(QuestRepository.class, new QuestRepository(sessionFactory));
        repositories.put(UserRepository.class, new UserRepository(sessionFactory));
    }
    
    public <V> V getRepository(Class<V> vClass) {
        return (V) repositories.get(vClass);
    }
    
}

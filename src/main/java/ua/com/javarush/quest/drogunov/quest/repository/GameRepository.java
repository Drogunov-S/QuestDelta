package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;

public class GameRepository extends AbstractRepository<Game>{
    public GameRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Game.class);
    }
}

package ua.com.javarush.quest.drogunov.quest.model.dao;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;

public class GameDAO extends BaseEntityDAO<Game> {
    public GameDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Game.class);
    }
}

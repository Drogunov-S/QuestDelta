package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;

public class GameRepository extends AbstractRepository<Game> {
    public GameRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Game.class);
    }
    
    @Override
    public Game save(Game entity) {
        getCurrentSession().beginTransaction();
        Game save = super.save(entity);
        getCurrentSession().getTransaction().commit();
        return save;
    }
    
    @Override
    public Game update(Game entity) {
        getCurrentSession().beginTransaction();
        Game update = super.update(entity);
        getCurrentSession().getTransaction().commit();
        return update;
    }
    
    @Override
    public Game getById(Long id) {
        getCurrentSession().beginTransaction();
        Game game = super.getById(id);
        getCurrentSession().getTransaction().commit();
        return game;
    }
    
    public Long getTrueAnswerId(Long gameId) {
        Session currentSession = getCurrentSession();
        currentSession.beginTransaction();
        Long trueAnswerId = currentSession
                .createQuery("SELECT g.lastQuestion.trueAnswer.id FROM Game g WHERE g.id = :GAME_ID", Long.class)
                .setParameter("GAME_ID", gameId)
                .getSingleResult();
        currentSession.getTransaction().commit();
        return trueAnswerId;
    }
}

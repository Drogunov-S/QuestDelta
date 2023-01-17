package ua.com.javarush.quest.drogunov.quest.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;

import java.util.Optional;

public class GameRepository extends AbstractRepository<Game>{

//    private
    
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
    
    /* public Optional<Game> getQuestGameByUserId(Game game) {
    getCurrentSession().beginTransaction();
        Game result = getCurrentSession().createQuery("from Game g where g.user.id = :USER_ID and g.quest.id = :QUEST_ID", Game.class)
                .setParameter("USER_ID", game.getUser().getId())
                .setParameter("QUEST_ID", game.getQuest().getId())
                .uniqueResult();
        getCurrentSession().getTransaction().commit();
    
    }*/
    
    
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
    /*
    public Long getTrueAnswerId(Long questionId) {
        Session currentSession = getCurrentSession();
        currentSession.beginTransaction();
        Long trueAnswerId = currentSession
                .createQuery("SELECT q.trueAnswer.id FROM Question q WHERE q.id = :QUESTION_ID", Long.class)
                .setParameter("QUESTION_ID", questionId)
                .getSingleResult();
        currentSession.getTransaction().commit();
        return trueAnswerId;
    }*/
}

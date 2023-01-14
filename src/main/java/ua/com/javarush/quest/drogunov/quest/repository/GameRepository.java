package ua.com.javarush.quest.drogunov.quest.repository;

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
    
   /* public Optional<Game> getQuestGameByUserId(Game game) {
    getCurrentSession().beginTransaction();
        Game result = getCurrentSession().createQuery("from Game g where g.user.id = :USER_ID and g.quest.id = :QUEST_ID", Game.class)
                .setParameter("USER_ID", game.getUser().getId())
                .setParameter("QUEST_ID", game.getQuest().getId())
                .uniqueResult();
        getCurrentSession().getTransaction().commit();
    
    }*/
}

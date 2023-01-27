package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.Session;
import ua.com.javarush.quest.drogunov.quest.mappers.Mappable;
import ua.com.javarush.quest.drogunov.quest.model.dto.GameDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Game;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;

public class TestMapping {
    public static void main(String[] args) {
        try (Session session = DbSession.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, 1L);
            System.out.println(user);
            UserDTO dto = Mappable.parse.from(user);
            System.out.println(dto);
            System.out.println("1 //////////////////////////////////////////");
            Game game = session.get(Game.class, 1L);
            System.out.println(game);
            GameDTO dto1 = Mappable.parse.from(game);
            System.out.println("--".repeat(20));
            System.out.println(dto1);
            session.getTransaction().commit();
            System.out.println("2 //////////////////////////////////////////");
            User from = Mappable.parse.from(dto);
            System.out.println(from);
            System.out.println("--".repeat(20));
            Game from1 = Mappable.parse.from(dto1);
            System.out.println(from1);
    
        } finally {
            DbSession.getSessionFactory().close();
        }
    }
}

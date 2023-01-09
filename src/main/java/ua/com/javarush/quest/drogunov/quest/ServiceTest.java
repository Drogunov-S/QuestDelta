package ua.com.javarush.quest.drogunov.quest;

import org.hibernate.SessionFactory;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.service.QuestService;
import ua.com.javarush.quest.drogunov.quest.util.DbSession;

import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = DbSession.getSessionFactory();
        QuestService questService = new QuestService(new QuestRepository(sessionFactory));
        List<QuestDTO> quests = questService.getQuests();
        System.out.println("////////////////////////////////////");
        System.out.println(quests);
        System.out.println("////////////////////////////////////");
    
    }
}

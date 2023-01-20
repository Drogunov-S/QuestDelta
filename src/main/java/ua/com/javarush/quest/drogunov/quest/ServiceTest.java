package ua.com.javarush.quest.drogunov.quest;

import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.service.QuestService;

import java.util.List;

public class ServiceTest {
    public static void main(String[] args) {
        QuestService questService = new QuestService();
        List<QuestDTO> quests = questService.getQuests();
        System.out.println("////////////////////////////////////");
        System.out.println(quests);
        System.out.println("////////////////////////////////////");
    
    }
}

package ua.com.javarush.quest.drogunov.quest.service;

import ua.com.javarush.quest.drogunov.quest.mappers.Mappable;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.util.RepositoryFactory;

import java.util.List;

public class QuestService {
    private final RepositoryFactory repositoryFactory = new RepositoryFactory();
    private final QuestRepository questRepository = repositoryFactory.getRepository(QuestRepository.class);
    
    public List<QuestDTO> getQuests() {
        List<Quest> quests = questRepository.getAll(0, 2);
        return Mappable.parse.from(quests);
    }
    
}

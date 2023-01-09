package ua.com.javarush.quest.drogunov.quest.service;

import ua.com.javarush.quest.drogunov.quest.mappers.Mapper;
import ua.com.javarush.quest.drogunov.quest.mappers.QuestMapper;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;

import java.util.List;

public class QuestService {
    private QuestRepository questRepository;
    private final Mapper<Quest, QuestDTO> mapper = new QuestMapper();
    
    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }
    
    public List<QuestDTO> getQuests() {
        List<Quest> quests = questRepository.getAll(0, 2);
        return mapper.parseEntityAll(quests);
    }
    
    public QuestDTO getQuestById(Long id) {
        return mapper.parseEntity(questRepository.getById(id));
    }
    
}

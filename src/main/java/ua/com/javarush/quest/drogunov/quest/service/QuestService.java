package ua.com.javarush.quest.drogunov.quest.service;

import ua.com.javarush.quest.drogunov.quest.dto.ui.QuestDto;
import ua.com.javarush.quest.drogunov.quest.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.mapping.Mapper;
import ua.com.javarush.quest.drogunov.quest.repository.QuestRepository;
import ua.com.javarush.quest.drogunov.quest.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public enum QuestService {
    INSTANCE;

    private final Repository<Quest> questRepository = QuestRepository.get();

    public Collection<QuestDto> getAll() {
        return questRepository.getAll()
                .map(Mapper.quest::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Optional<QuestDto> get(long id) {
        Quest quest = questRepository.get(id);
        return Mapper.quest.get(quest);
    }

}

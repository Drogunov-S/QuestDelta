package ua.com.javarush.quest.drogunov.quest.mapping;

import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.QuestDto;
import ua.com.javarush.quest.drogunov.quest.entity.Quest;

import java.util.Optional;

class QuestMapper implements Mapper<Quest, QuestDto> {

    @Override
    public Optional<QuestDto> get(Quest quest) {
        return quest != null
                ? Optional.of(QuestDto.with()
                .id(quest.getId())
                .name(quest.getName())
                .authorId(quest.getAuthorId())
                .build())
                : Optional.empty();
    }

    @Override
    public Quest parse(FormData formData) {
        Quest quest = Quest.with().build();
        return fill(quest, formData);
    }
}

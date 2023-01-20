package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.QuestDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestionDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.UserDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Quest;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;
import ua.com.javarush.quest.drogunov.quest.model.entity.User;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

class QuestMapper implements Mapper<Quest, QuestDTO> {
    private final Mapper<User, UserDTO> userMapper = new UserMapper();
    private final Mapper<Question, QuestionDTO> questionMapper = new QuestionMapper();
    
    @Override
    public QuestDTO parseEntity(Quest entity) {
        return QuestDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .author(userMapper.parseEntity(entity.getAuthor()))
                .questions(questionMapper.parseEntityAll(entity.getQuestions()))
                .build();
    }
    
    @Override
    public List<QuestDTO> parseEntityAll(Collection<Quest> entity) {
        return entity.stream()
                .map(this::parseEntity
                )
                .toList();
    }
    
    @Override
    public Quest parseDto(QuestDTO dto) {
        if (isNull(dto)) {
            return null;
        }
        Quest quest = new Quest();
        quest.setId(isNull(dto.getId()) ? null : dto.getId());
        quest.setName(isNull(dto.getName()) ? null : dto.getName());
        quest.setDescription(isNull(dto.getDescription()) ? null : dto.getDescription());
        quest.setAuthor(isNull(dto.getAuthor()) ? null : userMapper.parseDto(dto.getAuthor()));
        return quest;
    }
    
    @Override
    public List<Quest> parseDtoAll(Collection<QuestDTO> entity) {
        return null;
    }
}

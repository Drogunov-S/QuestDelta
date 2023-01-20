package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.AnswerDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

class AnswerMapper implements Mapper<Answer, AnswerDTO> {
    @Override
    public AnswerDTO parseEntity(Answer entity) {
        return AnswerDTO.builder()
                .id(entity.getId())
                .answer(entity.getAnswer())
                .build();
    }
    
    @Override
    public List<AnswerDTO> parseEntityAll(Collection<Answer> entity) {
        return entity
                .stream()
                .map(this::parseEntity)
                .toList();
    }
    
    @Override
    public Answer parseDto(AnswerDTO dto) {
        if (isNull(dto)) {
            return null;
        }
        Answer answer = new Answer();
        answer.setId(dto.getId());
        answer.setAnswer(dto.getAnswer());
        return answer;
    }
    
    @Override
    public List<Answer> parseDtoAll(Collection<AnswerDTO> entity) {
        return entity.stream()
                .map(this::parseDto)
                .toList();
    }
}

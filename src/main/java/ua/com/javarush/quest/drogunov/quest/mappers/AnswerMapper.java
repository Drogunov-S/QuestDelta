package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.AnswerDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;

import java.util.Collection;
import java.util.List;

public class AnswerMapper implements Mapper<Answer, AnswerDTO> {
    @Override
    public AnswerDTO parseEntity(Answer entity) {
        return AnswerDTO.builder()
                .answer(entity.getAnswer())
                .build();
    }
    
    @Override
    public List<AnswerDTO> parseEntityAll(Collection<Answer> entity) {
        return entity
                .stream()
                .map(answer -> AnswerDTO
                        .builder()
                        .answer(answer.getAnswer())
                        .build())
                .toList();
    }
    
    @Override
    public Answer parseDto(AnswerDTO dto) {
        return null;
    }
    
    @Override
    public List<AnswerDTO> parseDtoAll(Collection<Answer> entity) {
        return null;
    }
}

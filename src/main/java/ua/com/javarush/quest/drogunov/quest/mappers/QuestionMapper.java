package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.AnswerDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestionDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;

import java.util.*;

public class QuestionMapper implements Mapper<Question, QuestionDTO> {
    private final Mapper<Answer, AnswerDTO> answerMapper = new AnswerMapper();
    
    @Override
    public QuestionDTO parseEntity(Question entity) {
        return QuestionDTO.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .answers(new HashSet<>(answerMapper.parseEntityAll(entity.getAnswers())))
                .trueAnswer(answerMapper.parseEntity(entity.getTrueAnswer()))
                .build();
    }
    
    @Override
    public List<QuestionDTO> parseEntityAll(Collection<Question> entity) {
        return (List<QuestionDTO>) entity.stream()
                .map(question -> QuestionDTO
                        .builder()
                        .question(question.getQuestion())
                        .trueAnswer(answerMapper.parseEntity(question.getTrueAnswer()))
                        .answers(new HashSet<>(answerMapper.parseEntityAll(question.getAnswers())))
                        .build())
                .toList();
    }
    
    @Override
    public Question parseDto(QuestionDTO dto) {
        return null;
    }
    
    @Override
    public List<QuestionDTO> parseDtoAll(Collection<Question> entity) {
        return null;
    }
}

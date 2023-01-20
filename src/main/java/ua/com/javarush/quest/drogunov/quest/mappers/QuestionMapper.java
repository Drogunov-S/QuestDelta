package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.AnswerDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestionDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;

import java.util.*;

import static java.util.Objects.isNull;

class QuestionMapper implements Mapper<Question, QuestionDTO> {
    private final Mapper<Answer, AnswerDTO> answerMapper = new AnswerMapper();
    
    @Override
    public QuestionDTO parseEntity(Question entity) {
        return QuestionDTO.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .answers(answerMapper.parseEntityAll(entity.getAnswers()))
                .trueAnswer(answerMapper.parseEntity(entity.getTrueAnswer()))
                .build();
    }
    
    @Override
    public List<QuestionDTO> parseEntityAll(Collection<Question> entity) {
        return entity.stream()
                .map(this::parseEntity)
                .toList();
    }
    
    @Override
    public Question parseDto(QuestionDTO dto) {
        if (isNull(dto)) {
            return null;
        }
        Question question = new Question();
        question.setId(dto.getId());
        question.setQuestion(isNull(dto.getQuestion()) ? null : dto.getQuestion());
        question.setAnswers(isNull(dto.getAnswers()) ? null : answerMapper.parseDtoAll(dto.getAnswers()));
        question.setTrueAnswer(isNull(dto.getTrueAnswer()) ? null : answerMapper.parseDto(dto.getTrueAnswer()));
        return question;
    }
    
    @Override
    public List<Question> parseDtoAll(Collection<QuestionDTO> entity) {
        return entity.stream()
                .map(this::parseDto)
                .toList();
    }
}

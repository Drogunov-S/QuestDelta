package ua.com.javarush.quest.drogunov.quest.mappers;

import ua.com.javarush.quest.drogunov.quest.model.dto.AnswerDTO;
import ua.com.javarush.quest.drogunov.quest.model.dto.QuestionDTO;
import ua.com.javarush.quest.drogunov.quest.model.entity.Answer;
import ua.com.javarush.quest.drogunov.quest.model.entity.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class QuestionMapper implements Mapper<Question, QuestionDTO> {
    private Mapper<Answer, AnswerDTO> answerMapper = new AnswerMapper();
    
    @Override
    public QuestionDTO parseEntity(Question entity) {
        return null;
    }
    
    @Override
    public List<QuestionDTO> parseEntityAll(Collection<Question> entity) {
        return entity.stream()
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

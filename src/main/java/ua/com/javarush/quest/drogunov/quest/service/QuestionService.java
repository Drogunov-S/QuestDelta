package ua.com.javarush.quest.drogunov.quest.service;


import ua.com.javarush.quest.drogunov.quest.dto.ui.AnswerDto;
import ua.com.javarush.quest.drogunov.quest.dto.ui.QuestionDto;
import ua.com.javarush.quest.drogunov.quest.entity.Question;
import ua.com.javarush.quest.drogunov.quest.mapping.Mapper;
import ua.com.javarush.quest.drogunov.quest.repository.QuestionRepository;
import ua.com.javarush.quest.drogunov.quest.repository.Repository;

import java.util.List;
import java.util.Optional;

public enum QuestionService {
    INSTANCE;

    private final Repository<Question> questionRepository = QuestionRepository.get();

    public Optional<QuestionDto> get(long id) {
        Question question = questionRepository.get(id);
        List<AnswerDto> answerDto = question.getAnswers().stream()
                .map(Mapper.answer::get)
                .map(Optional::orElseThrow)
                .toList();
        Optional<QuestionDto> questionDto = Mapper.question.get(question);
        questionDto.orElseThrow().setAnswers(answerDto);
        return questionDto;
    }
}

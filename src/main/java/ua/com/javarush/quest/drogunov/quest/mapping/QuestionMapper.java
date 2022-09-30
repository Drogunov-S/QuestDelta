package ua.com.javarush.quest.drogunov.quest.mapping;

import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.QuestionDto;
import ua.com.javarush.quest.drogunov.quest.entity.Question;

import java.util.Optional;

class QuestionMapper implements Mapper<Question, QuestionDto> {

    @Override
    public Optional<QuestionDto> get(Question question) {
        return question != null
                ? Optional.of(QuestionDto.with()
                .id(question.getId())
                .questId(question.getQuestId())
                .image(question.getImage())
                .text(question.getText())
                .build())
                : Optional.empty();
    }

    @Override
    public Question parse(FormData formData) {
        Question question = Question.with().build();
        return fill(question, formData);
    }
}

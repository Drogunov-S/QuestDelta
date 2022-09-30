package ua.com.javarush.quest.drogunov.quest.mapping;

import ua.com.javarush.quest.drogunov.quest.dto.FormData;
import ua.com.javarush.quest.drogunov.quest.dto.ui.AnswerDto;
import ua.com.javarush.quest.drogunov.quest.entity.Answer;

import java.util.Optional;

class AnswerMapper implements Mapper<Answer, AnswerDto> {

    @Override
    public Optional<AnswerDto> get(Answer answer) {
        return (answer != null)
                ? Optional.of(AnswerDto.with()
                .id(answer.getId())
                .text(answer.getText())
                .nextQuestionId(answer.getNextQuestionId())
                .build())
                : Optional.empty();
    }

    @Override
    public Answer parse(FormData formData) {
        Answer quest = Answer.with().build();
        return fill(quest, formData);
    }
}

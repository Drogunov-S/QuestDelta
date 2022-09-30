package ua.com.javarush.quest.drogunov.quest.dto.ui;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class AnswerDto {
    private Long id;
    private String text;
    Long nextQuestionId;
}

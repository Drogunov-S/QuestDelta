package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class QuestionDTO extends BaseEntityDTO {
    private String question;
    private List<AnswerDTO> answers;
    private AnswerDTO trueAnswer;
}

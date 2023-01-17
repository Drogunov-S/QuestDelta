package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
public class QuestionDTO extends BaseEntityDTO {
    private String question;
    private List<AnswerDTO> answers;
    private AnswerDTO trueAnswer;
}

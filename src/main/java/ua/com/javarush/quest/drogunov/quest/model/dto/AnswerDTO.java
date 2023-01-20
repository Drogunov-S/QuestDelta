package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class AnswerDTO extends BaseEntityDTO {
    private String answer;
}

package ua.com.javarush.quest.drogunov.quest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Answer extends Entity {

    protected Long id;
    protected Long questionId;
    protected String text;
    protected Long nextQuestionId;

}

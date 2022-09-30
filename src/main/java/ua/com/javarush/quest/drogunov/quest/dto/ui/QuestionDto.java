package ua.com.javarush.quest.drogunov.quest.dto.ui;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.javarush.quest.drogunov.quest.entity.GameState;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class QuestionDto {
    private Long id;
    private Long questId;
    private String image;
    private String text;
    private GameState state;
    private Collection<AnswerDto> answers;
}

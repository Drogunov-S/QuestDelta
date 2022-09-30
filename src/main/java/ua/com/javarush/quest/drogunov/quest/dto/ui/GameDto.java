package ua.com.javarush.quest.drogunov.quest.dto.ui;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.javarush.quest.drogunov.quest.entity.GameState;

@Data
@EqualsAndHashCode
@Builder(builderMethodName = "with")
public class GameDto {
    Long id;
    Long questId;
    Long userId;
    QuestionDto question;
    GameState gameState;
}

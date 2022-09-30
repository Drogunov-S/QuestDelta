package ua.com.javarush.quest.drogunov.quest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Game extends Entity {
    protected Long id;
    protected Long questId;
    protected Long userId;
    protected Long currentQuestionId;
    GameState gameState;
}

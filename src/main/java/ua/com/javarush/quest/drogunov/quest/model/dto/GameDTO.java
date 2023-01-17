package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import ua.com.javarush.quest.drogunov.quest.model.entity.GameState;

@SuperBuilder
@Data
public class GameDTO extends BaseEntityDTO {
    private UserDTO user;
    private QuestDTO quest;
    private QuestionDTO lastQuestion;
    private GameState gameState;
}

package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class GameDTO extends BaseEntityDTO {
    private UserDTO user;
    private QuestDTO quest;
    private QuestionDTO lastQuestion;
}

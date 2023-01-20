package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class QuestDTO extends BaseEntityDTO {
    private String name;
    private String description;
    private List<QuestionDTO> questions;
    private UserDTO author;
    
    @Override
    public String toString() {
        return "QuestDTO{" +
                "name='" + name + '\'' +
                ", questions=" + questions +
                ", author=" + author +
                "} " + super.toString();
    }
}


package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class QuestDTO extends BaseEntityDTO {
    private Long id;
    private String name;
    private String description;
    private List<QuestionDTO> questions;
    private UserDTO author;
    
    @Override
    public String toString() {
        return "QuestDTO{" +
                "name='" + name + '\'' +
                ", questions=" + questions.size() +
                ", author=" + author.getLogin() +
                "} " + super.toString();
    }
}


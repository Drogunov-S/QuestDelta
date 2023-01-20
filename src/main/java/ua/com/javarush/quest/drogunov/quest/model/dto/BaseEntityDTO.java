package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class BaseEntityDTO {
    private Long id;
    
    @Override
    public String toString() {
        return "id=" + id + ", ";
    }
}

package ua.com.javarush.quest.drogunov.quest.model.dto;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
public abstract class BaseEntityDTO {
    private Long id;
    
    @Override
    public String toString() {
        return "id=" + id + ", ";
    }
}

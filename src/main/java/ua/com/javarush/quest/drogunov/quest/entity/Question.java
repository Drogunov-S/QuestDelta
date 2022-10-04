package ua.com.javarush.quest.drogunov.quest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Question extends Entity {

    Long id;
    Long questId;
    String text;
    GameState state;
    final Collection<Answer> answers = new ArrayList<>();

    public String getImage() {
        return "image-" + id;
    }


}

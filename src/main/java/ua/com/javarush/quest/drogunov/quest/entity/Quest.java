package ua.com.javarush.quest.drogunov.quest.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")
public class Quest extends Entity {
    String name;
    Long id;
    Long authorId;
    Long startQuestionId;
    final Collection<Question> questions = new ArrayList<>();
}

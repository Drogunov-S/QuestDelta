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
    protected String name;
    protected Long id;
    protected Long authorId;
    protected Long startQuestionId;
    final Collection<Question> questions = new ArrayList<>();
}

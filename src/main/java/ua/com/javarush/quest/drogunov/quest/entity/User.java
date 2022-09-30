package ua.com.javarush.quest.drogunov.quest.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")
public class User extends Entity {
    protected Long id;
    protected String login;
    protected String password;
    protected Role role;
    final Collection<Game> games = new ArrayList<>();
    final Collection<Quest> quests = new ArrayList<>();

    public String getImage() {
        return "image-" + id;
    }

}

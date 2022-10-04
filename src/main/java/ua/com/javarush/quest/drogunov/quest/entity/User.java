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
    Long id;
    String login;
    String password;
    Role role;
    final Collection<Game> games = new ArrayList<>();
    final Collection<Quest> quests = new ArrayList<>();

    public String getImage() {
        return "image-" + id;
    }

}

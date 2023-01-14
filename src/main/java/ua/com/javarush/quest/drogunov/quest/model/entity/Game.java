package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "games", schema = "quest")
public class Game extends BaseEntity {
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "quest_id", referencedColumnName = "id")
    private Quest quest;
    @OneToOne
    @JoinColumn(name = "last_question_id")
    private Question lastQuestion;
}

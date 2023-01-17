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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "quest_id", referencedColumnName = "id")
    private Quest quest;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_question_id")
    private Question lastQuestion;
    @Column(name = "game_state", columnDefinition = "enum('START', 'PLAY', 'END')")
    @Enumerated(EnumType.STRING)
    private GameState gameState;
}

package ua.com.javarush.quest.drogunov.quest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@Table(name = "quests", schema = "quest")
public class Quest extends BaseEntity {
    @Column(name = "name")
    String name;
    @OneToMany
    @JoinColumn(name = "quest_id")
    List<Question> questions;
    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    User author;
}

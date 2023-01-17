package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_id")
    private List<Question> questions;
    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
}

package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@Table(name = "questions", schema = "quest")
public class Question extends BaseEntity {
    @Column(name = "question", columnDefinition = "text")
    private String question;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Set<Answer> answers;
    @OneToOne
    @JoinColumn(name = "true_answer_id")
    private Answer trueAnswer;
}

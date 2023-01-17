package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    private List<Answer> answers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "true_answer_id")
    private Answer trueAnswer;
    
    @Override
    public String toString() {
        return "\nQuestion{" +
                "question='" + question + '\'' +
                " } " + super.toString() + this.getClass().getName();
    }
}

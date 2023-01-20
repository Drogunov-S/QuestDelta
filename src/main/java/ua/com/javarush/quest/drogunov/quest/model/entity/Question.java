package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Question question = (Question) o;
        return getId() != null && Objects.equals(getId(), question.getId());
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

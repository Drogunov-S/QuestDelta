package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "answers", schema = "quest")
public class Answer extends BaseEntity {
    @Column(name = "answer", columnDefinition = "text")
    private String answer;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Answer answer = (Answer) o;
        return getId() != null && Objects.equals(getId(), answer.getId());
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

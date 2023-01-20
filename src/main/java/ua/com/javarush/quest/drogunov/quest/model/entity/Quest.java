package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Quest quest = (Quest) o;
        return getId() != null && Objects.equals(getId(), quest.getId());
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

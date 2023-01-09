package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "answers", schema = "quest")
public class Answer extends BaseEntity {
    @Column(name = "answer", columnDefinition = "text")
    private String answer;
}

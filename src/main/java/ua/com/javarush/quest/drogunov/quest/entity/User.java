package ua.com.javarush.quest.drogunov.quest.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "users", schema = "quest")
public class User extends BaseEntity {
    @Column(name = "login", nullable = false)
    String login;
    @Column(name = "password")
    String password;
    @Column(name = "role", nullable = false, columnDefinition = "org.hibernate.type.EnumType")
    @Enumerated(EnumType.STRING)
    Role role;
}

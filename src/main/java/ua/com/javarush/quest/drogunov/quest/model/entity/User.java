package ua.com.javarush.quest.drogunov.quest.model.entity;

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
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role", nullable = false, columnDefinition = "org.hibernate.type.EnumType")
    @Enumerated(EnumType.STRING)
    private Role role;
}

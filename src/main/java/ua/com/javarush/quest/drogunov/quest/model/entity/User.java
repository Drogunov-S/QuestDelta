package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "users", schema = "quest")
public class User extends BaseEntity {
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role", nullable = false, columnDefinition = "enum('GUEST', 'USER', 'REDACTOR', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private Role role;
}

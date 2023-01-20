package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package ua.com.javarush.quest.drogunov.quest.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;
    
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", ";
    }
}

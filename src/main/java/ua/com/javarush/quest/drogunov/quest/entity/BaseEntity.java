package ua.com.javarush.quest.drogunov.quest.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_date")
    @CreationTimestamp
    LocalDateTime createDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    LocalDateTime updateDate;
}

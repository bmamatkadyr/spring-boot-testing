package springboottesting.pizza.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private ZonedDateTime createdAt;


    @PrePersist
    public void prePersist() {
        createdAt = ZonedDateTime.now();
    }
}

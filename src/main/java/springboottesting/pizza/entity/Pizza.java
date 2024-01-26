package springboottesting.pizza.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "pizzas")
@Entity
public class Pizza extends BaseEntity {
    private String name;
    private String description;
    private Double price;

    public Pizza() {
    }

    public Pizza(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

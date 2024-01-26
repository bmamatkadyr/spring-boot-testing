package springboottesting.pizza.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboottesting.pizza.entity.Pizza;

import java.util.UUID;

public interface PizzaRepo extends JpaRepository<Pizza, UUID> {
}

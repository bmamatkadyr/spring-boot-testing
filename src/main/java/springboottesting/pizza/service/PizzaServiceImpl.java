package springboottesting.pizza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springboottesting.pizza.dto.PizzaRequest;
import springboottesting.pizza.entity.Pizza;
import springboottesting.pizza.error.PizzaNotFoundException;
import springboottesting.pizza.helper.Pagination;
import springboottesting.pizza.repo.PizzaRepo;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepo pizzaRepo;

    @Override
    public Pizza save(PizzaRequest pizzaRequest) {
        Pizza pizza = pizzaRequest.buildPizza();
        return pizzaRepo.save(pizza);
    }

    @Override
    public Pagination<Pizza> findAll(int page, int size) {
        Page<Pizza> pizzas = pizzaRepo.findAll(PageRequest.of(page, size));
        return Pagination.of(pizzas);
    }

    @Override
    public Pizza findById(UUID id) {
        return pizzaRepo.findById(id)
                .orElseThrow(PizzaNotFoundException::new);
    }

    @Override
    public void deleteById(UUID id) {
        Pizza pizza = findById(id);
        pizzaRepo.delete(pizza);
    }

    @Override
    public Pizza update(UUID id, PizzaRequest pizzaRequest) {
        Pizza pizza = findById(id);

        pizzaRequest.update(pizza);

        return pizzaRepo.save(pizza);
    }
}

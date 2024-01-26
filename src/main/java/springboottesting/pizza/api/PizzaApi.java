package springboottesting.pizza.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springboottesting.pizza.dto.PizzaRequest;
import springboottesting.pizza.entity.Pizza;
import springboottesting.pizza.helper.Pagination;
import springboottesting.pizza.service.PizzaService;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/pizzas")
@RestController
public class PizzaApi {

    private final PizzaService pizzaService;

    // save
    @PostMapping
    public Pizza save(@Valid @RequestBody PizzaRequest pizzaRequest) {
        return pizzaService.save(pizzaRequest);
    }

    // findAll
    @GetMapping
    public Pagination<Pizza> findAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return pizzaService.findAll(page, size);
    }

    // findById
    @GetMapping("/{id}")
    public Pizza findById(@PathVariable UUID id) {
        return pizzaService.findById(id);
    }

    // deleteById
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        pizzaService.deleteById(id);
    }

    // update
    @PutMapping("/{id}")
    public Pizza update(@PathVariable UUID id,
                        @Valid @RequestBody PizzaRequest pizzaRequest) {
        return pizzaService.update(id, pizzaRequest);
    }
}

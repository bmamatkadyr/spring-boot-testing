package springboottesting.pizza.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import springboottesting.pizza.entity.Pizza;

public record PizzaRequest(
        @NotBlank(message = "Name is required!")
        String name,
        @NotBlank(message = "Description is required!")
        String description,
        @NotNull(message = "Price is required!")
        Double price
) {

    public Pizza buildPizza() {
        Pizza pizza = new Pizza();
        pizza.setName(name);
        pizza.setDescription(description);
        pizza.setPrice(price);
        return pizza;
    }

    public void update(Pizza pizza) {
        pizza.setName(name);
        pizza.setDescription(description);
        pizza.setPrice(price);
    }
}

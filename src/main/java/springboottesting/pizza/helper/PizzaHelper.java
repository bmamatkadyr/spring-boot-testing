package springboottesting.pizza.helper;

import springboottesting.pizza.entity.Pizza;

import java.util.List;

public class PizzaHelper {

    public static List<Pizza> getRandomPizzas() {
        return List.of(
                new Pizza("Margherita", "Tomato sauce, mozzarella", 5.0),
                new Pizza("Marinara", "Tomato sauce, garlic", 4.5),
                new Pizza("Quattro Stagioni", "Tomato sauce, mozzarella, mushrooms, ham, artichokes, olives, oregano", 6.5),
                new Pizza("Carbonara", "Tomato sauce, mozzarella, parmesan, eggs, bacon", 7.5),
                new Pizza("Frutti di Mare", "Tomato sauce, seafood", 7.5)
        );
    }
}

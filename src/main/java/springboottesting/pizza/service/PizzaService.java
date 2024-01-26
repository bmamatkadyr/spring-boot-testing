package springboottesting.pizza.service;

import springboottesting.pizza.dto.PizzaRequest;
import springboottesting.pizza.entity.Pizza;
import springboottesting.pizza.helper.Pagination;

import java.util.UUID;

/**
 * PizzaService is an interface that defines the operations for managing pizzas.
 */
public interface PizzaService {

    /**
     * Saves a new pizza.
     *
     * @param pizzaRequest the pizza request containing the details of the pizza to be saved
     * @return the saved pizza
     */
    Pizza save(PizzaRequest pizzaRequest);

    /**
     * Retrieves all pizzas with pagination.
     *
     * @param page the page number to retrieve
     * @param size the number of pizzas per page
     * @return a Pagination object containing the list of pizzas and pagination details
     */
    Pagination<Pizza> findAll(int page, int size);

    /**
     * Retrieves a pizza by its ID.
     *
     * @param id the ID of the pizza to retrieve
     * @return the pizza with the given ID
     */
    Pizza findById(UUID id);

    /**
     * Deletes a pizza by its ID.
     *
     * @param id the ID of the pizza to delete
     */
    void deleteById(UUID id);

    /**
     * Updates a pizza by its ID.
     *
     * @param id the ID of the pizza to update
     * @param pizzaRequest the pizza request containing the new details of the pizza
     * @return the updated pizza
     */
    Pizza update(UUID id, PizzaRequest pizzaRequest);
}

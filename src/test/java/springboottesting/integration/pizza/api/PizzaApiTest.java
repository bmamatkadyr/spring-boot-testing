package springboottesting.integration.pizza.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import springboottesting.config.PostgreSQLContainerTestExecutionListener;
import springboottesting.pizza.dto.PizzaRequest;
import springboottesting.pizza.entity.Pizza;
import springboottesting.pizza.helper.PizzaHelper;
import springboottesting.pizza.repo.PizzaRepo;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestExecutionListeners(
        listeners = {PostgreSQLContainerTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
class PizzaApiTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private PizzaRepo pizzaRepo;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:%d".formatted(port);
        pizzaRepo.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "Margherita, Description for Margherita., 10.0",
            "Pepperoni, Description for Pepperoni., 15.0",
            "Hawaiian, Description for Hawaiian., 12.0"
    })
    void save(String name, String description, double price) {
        PizzaRequest pizzaRequest = new PizzaRequest(name, description, price);

        String path = "/api/pizzas";
        var response = given()
                .contentType(ContentType.JSON)
                .body(pizzaRequest)
                .when()
                .post(path)
                .then()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("description", equalTo(description))
                .body("price", equalTo((float) price));

        // check if pizza is present
        UUID id = UUID.fromString(response.extract().path("id").toString());
        assertTrue(pizzaRepo.findById(id).isPresent(), "Pizza is not present.");
    }

    @Test
    void findAll() {
        List<Pizza> randomPizzas = PizzaHelper.getRandomPizzas();

        // save all pizzas
        pizzaRepo.saveAll(randomPizzas);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/pizzas")
                .then()
                .statusCode(200)
                .body("data", hasSize(randomPizzas.size()));
    }

    @Test
    void findById() {
        Pizza newPizza = new Pizza("Margherita", "Tomato sauce, mozzarella", 5.0);
        Pizza savedPizza = pizzaRepo.save(newPizza);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/pizzas/%s".formatted(savedPizza.getId()))
                .then()
                .statusCode(200)
                .body("name", equalTo(newPizza.getName()))
                .body("description", equalTo(newPizza.getDescription()))
                .body("price", equalTo(newPizza.getPrice().floatValue()));
    }

    @Test
    void deleteById() {
        Pizza newPizza = new Pizza("Margherita", "Tomato sauce, mozzarella", 5.0);
        Pizza savedPizza = pizzaRepo.save(newPizza);

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/pizzas/%s".formatted(savedPizza.getId()))
                .then()
                .statusCode(200);

        // check if pizza is deleted
        assertTrue(pizzaRepo.findById(savedPizza.getId()).isEmpty(), "Pizza is not deleted.");
    }

    @Test
    void update() {
        Pizza newPizza = new Pizza("Margherita", "Tomato sauce, mozzarella", 5.0);
        Pizza savedPizza = pizzaRepo.save(newPizza);

        PizzaRequest pizzaRequest = new PizzaRequest("Pepperoni", "Tomato sauce, mozzarella, pepperoni", 10.0);

        given()
                .contentType(ContentType.JSON)
                .body(pizzaRequest)
                .when()
                .put("/api/pizzas/%s".formatted(savedPizza.getId()))
                .then()
                .statusCode(200)
                .body("name", equalTo(pizzaRequest.name()))
                .body("description", equalTo(pizzaRequest.description()))
                .body("price", equalTo(pizzaRequest.price().floatValue()));

        // check if pizza is updated
        Pizza updatedPizza = pizzaRepo.findById(savedPizza.getId()).orElseThrow();
        assertEquals(updatedPizza.getName(), pizzaRequest.name(), "Pizza is not updated.");
        assertEquals(updatedPizza.getDescription(), pizzaRequest.description(), "Pizza is not updated.");
        assertEquals(updatedPizza.getPrice(), pizzaRequest.price(), "Pizza is not updated.");
    }
}
package springboottesting.pizza.error;

public class PizzaNotFoundException extends RuntimeException {
    public static final String ERROR_MESSAGE = "Pizza not found!";

    public PizzaNotFoundException() {
        super(ERROR_MESSAGE);
    }

    public PizzaNotFoundException(Throwable cause) {
        super(ERROR_MESSAGE, cause);
    }
}

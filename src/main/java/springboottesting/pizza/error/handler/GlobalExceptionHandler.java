package springboottesting.pizza.error.handler;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springboottesting.pizza.error.PizzaNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PizzaNotFoundException.class)
    public ProblemDetail handlePizzaNotFoundException(PizzaNotFoundException e) {
        var problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        var problemDetail = ProblemDetail.forStatus(500);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }
}

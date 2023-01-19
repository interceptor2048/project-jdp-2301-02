package com.kodilla.ecommercee.exception;

import com.kodilla.ecommercee.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>("Order with given id doesn't exist.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundWhileCreatingOrderException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundWhileCreatingOrderException exception) {
        return new ResponseEntity<>("Cart with given Id doesn't exist, couldn't create Order", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderWithGivenUserNotFoundException.class)
    public ResponseEntity<Object> handleOrderWithGivenUserNotFoundException(OrderWithGivenUserNotFoundException exception) {
        return new ResponseEntity<>("Order with given userId doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id hasn't been found.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException exception) {
        return new ResponseEntity<>("Cart with given id hasn't been found.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>("Product with given id hasn't been found.", HttpStatus.BAD_REQUEST);
    }
}

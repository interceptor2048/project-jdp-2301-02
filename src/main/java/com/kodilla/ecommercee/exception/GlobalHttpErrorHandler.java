package com.kodilla.ecommercee.exception;

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
}

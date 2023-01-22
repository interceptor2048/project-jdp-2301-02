package com.kodilla.ecommercee.exception;

import com.kodilla.ecommercee.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<Object> handlerEmptyFieldException(EmptyFieldException excetpion) {
        return new ResponseEntity<>("One or more of required fields is incorrect", HttpStatus.OK);
    }

    @ExceptionHandler(RecordNotExistsException.class)
    public ResponseEntity<Object> handlerRecordNoExistsException(RecordNotExistsException excetpion) {
        return new ResponseEntity<>("Object with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<Object> handlerRecordExistsException(RecordExistsException excetpion) {
        return new ResponseEntity<>("Object already exist", HttpStatus.BAD_REQUEST);
    }

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

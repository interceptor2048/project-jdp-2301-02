package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<Object> handlerEmptyFieldException(EmptyFieldException excetpion) {
        return new ResponseEntity<>("One or more of required fields is incorrect", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotExistsException.class)
    public ResponseEntity<Object> handlerRecordNoExistsException(RecordNotExistsException excetpion) {
        return new ResponseEntity<>("Object with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<Object> handlerRecordExistsException(RecordExistsException excetpion) {
        return new ResponseEntity<>("Object already exist", HttpStatus.BAD_REQUEST);
    }
}
package com.ataipek.todo.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> customHandleApplicationException(ApplicationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(422));
    }
}
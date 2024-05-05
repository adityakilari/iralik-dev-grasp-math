package com.iralik.graspmath.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class exceptionadvice {
    @ExceptionHandler(GMcustomException.class)
    public ResponseEntity<List<String>> handleCustomException(GMcustomException ex) {
        List<String> customMessages = ex.getRegistrationErrorMessages();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessages);
    }
}

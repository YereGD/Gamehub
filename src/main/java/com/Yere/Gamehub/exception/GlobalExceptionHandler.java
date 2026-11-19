package com.Yere.Gamehub.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("message", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode())
        .body(Map.of("message", ex.getReason()));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessage.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler (GameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleGameNotFoundException(GameNotFoundException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler (InvalidGameException.class)
    public ResponseEntity<Map<String, String>> handleInvalidGameException(InvalidGameException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("message", ex.getMessage()));
    }

}

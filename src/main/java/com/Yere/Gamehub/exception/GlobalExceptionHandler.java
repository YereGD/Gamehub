package com.Yere.Gamehub.exception;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler (InvalidGameException.class)
    public ResponseEntity<Map<String, String>> handleInvalidGameException(InvalidGameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler (GameAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleGameAlreadyExistsException(GameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        Throwable cause = ex;

        while (cause != null) {
            if (cause instanceof ConstraintViolationException constraintEx
                    && "23505".equals(constraintEx.getSQLState())
                    && "ux_game_entity_name_ci".equals(
                        constraintEx.getConstraintName())) {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of(
                            "message",
                            "A game with that name already exists"
                        ));
            }

            cause = cause.getCause();
        }
        throw ex;
    }
}

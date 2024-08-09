package org.example.apiviagens.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TripsAndDestinationsExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }
}

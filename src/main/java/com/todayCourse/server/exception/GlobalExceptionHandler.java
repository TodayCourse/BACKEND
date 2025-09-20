package com.todayCourse.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessLogicException(BusinessLogicException ex) {
        Map<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("status", ex.getExceptionCode().getStatus());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("error", ex.getExceptionCode().name());
        errorResponse.put("message", ex.getExceptionCode().getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getExceptionCode().getStatus()));
    }
}

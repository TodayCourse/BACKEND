package com.todayCourse.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

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

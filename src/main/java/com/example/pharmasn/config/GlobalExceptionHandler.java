package com.example.pharmasn.config;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.pharmasn.user.dtos.ErrorResponse;
import com.example.pharmasn.user.exceptions.EmailAlreadyUsedException;;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<?> handleEmailAlreadyUsed(EmailAlreadyUsedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), 409);
        return ResponseEntity
                .status(409) // HTTP conflict
                .body(errorResponse);
    }
}
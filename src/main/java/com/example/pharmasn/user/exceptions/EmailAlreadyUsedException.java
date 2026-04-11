package com.example.pharmasn.user.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    
    public EmailAlreadyUsedException(String message) {
        super(message);
    }

}

package com.example.pharmasn.medicament;

public class MedicamentNotFoundException extends RuntimeException {
    public MedicamentNotFoundException(String message) {
        super(message);
    }
}
package com.example.pharmasn.pharmacie;

public class PharmacieNotFoundException extends RuntimeException {
    public PharmacieNotFoundException(String message) {
        super(message);
    }
}

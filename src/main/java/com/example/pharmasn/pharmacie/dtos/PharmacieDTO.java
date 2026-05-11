package com.example.pharmasn.pharmacie.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacieDTO {
    private String name;
    private Long ownerId;
    private String address;
    private String phoneNumber;
}

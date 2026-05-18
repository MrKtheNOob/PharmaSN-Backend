package com.example.pharmasn.stock.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockResponseDTO {
    private Long id;
    private String pharmacieName;
    private String pharmacieAddress;
    private String pharmaciePhoneNumber;
    private String medicamentName;
    private Integer quantity;
    private Double price;
}

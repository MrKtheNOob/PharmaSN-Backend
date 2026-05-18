package com.example.pharmasn.stock.dtos;

import lombok.Data;

@Data
public class StockRequestDTO {
    private Long medicamentId;
    private Integer quantity;
    private Double price;
}

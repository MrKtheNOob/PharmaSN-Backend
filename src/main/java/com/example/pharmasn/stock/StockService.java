package com.example.pharmasn.stock;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {
    private final StockRepository stockRepository;


    public Stock createOrUpdateStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public java.util.Optional<Stock> getStockByMedicamentId(Long medicamentId) {
        return stockRepository.findByMedicamentId(medicamentId);
    }
    
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
    public java.util.List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public void addStock(Long medicamentId, Integer quantity) {
        Stock stock = stockRepository.findByMedicamentId(medicamentId)
                .orElseThrow(() -> new RuntimeException("Stock not found for medicament ID: " + medicamentId));
        
        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.save(stock);
    }
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

}
package com.example.pharmasn.stock;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/stock")
@AllArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping
    public java.util.List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{medicamentId}")
    public java.util.Optional<Stock> getStockByMedicamentId(@PathVariable Long medicamentId) {
        return stockService.getStockByMedicamentId(medicamentId);  
    }

    @PostMapping("/{medicamentId}/{quantity}")
    public void addStock(@PathVariable Long medicamentId, @PathVariable Integer quantity) {
        stockService.addStock(medicamentId, quantity);
    }

    @PostMapping("/")
    public Stock createStock(Stock stock) {
        return stockService.createStock(stock);
    }
    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
    }

    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        stock.setId(id);
        return stockService.createOrUpdateStock(stock);
    }

}

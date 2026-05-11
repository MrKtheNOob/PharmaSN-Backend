package com.example.pharmasn.stock;

import com.example.pharmasn.stock.dtos.StockRequestDTO;
import com.example.pharmasn.stock.dtos.StockResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public List<StockResponseDTO> getAll() {
        return stockService.getAllAvailable();
    }

    @GetMapping("/search")
    public List<StockResponseDTO> search(@RequestParam String name) {
        return stockService.searchStocks(name);
    }

    @GetMapping("/my-inventory")
    public List<StockResponseDTO> getMyInventory(Authentication authentication) {
        return stockService.getStocksByPharmacistEmail(authentication.getName());
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody StockRequestDTO dto, Authentication authentication) {
        return ResponseEntity.ok(stockService.createStock(dto, authentication.getName()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Stock> updateStock(
            @PathVariable Long id,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Double price,
            Authentication authentication) {
        return ResponseEntity.ok(stockService.updateStock(id, quantity, price, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id, Authentication authentication) {
        stockService.deleteStock(id, authentication.getName());
        return ResponseEntity.ok().build();
    }
}

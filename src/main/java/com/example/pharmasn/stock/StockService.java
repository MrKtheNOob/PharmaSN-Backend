package com.example.pharmasn.stock;

import com.example.pharmasn.medicament.Medicament;
import com.example.pharmasn.medicament.MedicamentRepository;
import com.example.pharmasn.pharmacie.Pharmacie;
import com.example.pharmasn.pharmacie.PharmacieRepository;
import com.example.pharmasn.stock.dtos.StockRequestDTO;
import com.example.pharmasn.stock.dtos.StockResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final PharmacieRepository pharmacieRepository;
    private final MedicamentRepository medicamentRepository;

    @Transactional(readOnly = true)
    public List<StockResponseDTO> getAllAvailable() {
        return stockRepository.findAll().stream()
                .filter(s -> s.getQuantity() > 0)
                .limit(10)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StockResponseDTO> searchStocks(String query) {
        return stockRepository.searchAvailableStocks(query).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StockResponseDTO> getStocksByPharmacistEmail(String email) {
        Pharmacie pharmacie = pharmacieRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new RuntimeException("No pharmacy found for this user"));
        
        return stockRepository.findByPharmacieId(pharmacie.getId()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Stock createStock(StockRequestDTO dto, String email) {
        Pharmacie pharmacie = pharmacieRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));
        
        Medicament medicament = medicamentRepository.findById(dto.getMedicamentId())
                .orElseThrow(() -> new RuntimeException("Medicament not found"));

        Stock stock = new Stock();
        stock.setPharmacie(pharmacie);
        stock.setMedicament(medicament);
        stock.setQuantity(dto.getQuantity());
        stock.setPrice(dto.getPrice());
        
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long id, Integer quantity, Double price, String email) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        
        if (!stock.getPharmacie().getOwner().getEmail().equals(email)) {
            throw new RuntimeException("Access denied: You don't own this pharmacy");
        }
        
        if (quantity != null) stock.setQuantity(quantity);
        if (price != null) stock.setPrice(price);
        
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id, String email) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        
        if (!stock.getPharmacie().getOwner().getEmail().equals(email)) {
            throw new RuntimeException("Access denied");
        }
        
        stockRepository.deleteById(id);
    }

    private StockResponseDTO convertToDTO(Stock stock) {
        return new StockResponseDTO(
                stock.getId(),
                stock.getPharmacie().getName(),
                stock.getPharmacie().getAddress(),
                stock.getPharmacie().getPhoneNumber(),
                stock.getMedicament().getName(),
                stock.getQuantity(),
                stock.getPrice()
        );
    }
}

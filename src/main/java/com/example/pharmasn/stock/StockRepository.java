package com.example.pharmasn.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    java.util.Optional<Stock> findByMedicamentId(Long medicamentId);
    
} 
package com.example.pharmasn.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    
    @Query("SELECT s FROM Stock s JOIN s.medicament m " +
           "WHERE (LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(m.activePrinciple) LIKE LOWER(CONCAT('%', :query, '%'))) " +
           "AND s.quantity > 0")
    List<Stock> searchAvailableStocks(@Param("query") String query);

    List<Stock> findByPharmacieId(Long pharmacieId);
}

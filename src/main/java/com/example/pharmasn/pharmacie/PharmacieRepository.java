package com.example.pharmasn.pharmacie;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacieRepository extends JpaRepository<Pharmacie,Long>{
    List<Pharmacie> findByNameContainingIgnoreCase(String name);
}

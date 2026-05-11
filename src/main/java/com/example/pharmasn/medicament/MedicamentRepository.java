package com.example.pharmasn.medicament;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
    Optional<List<Medicament>> findByName(String name);
    List<Medicament> findByNameContainingIgnoreCase(String name);
}

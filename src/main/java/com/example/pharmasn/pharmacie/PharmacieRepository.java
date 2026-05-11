package com.example.pharmasn.pharmacie;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharmasn.user.entity.User;
import java.util.Optional;

@Repository
public interface PharmacieRepository extends JpaRepository<Pharmacie,Long>{
    List<Pharmacie> findByNameContainingIgnoreCase(String name);
    Optional<Pharmacie> findByOwnerId(Long ownerId);
    Optional<Pharmacie> findByOwnerEmail(String email);
}

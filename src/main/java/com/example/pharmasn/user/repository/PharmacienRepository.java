package com.example.pharmasn.user.repository;

import com.example.pharmasn.user.entity.Pharmacien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacienRepository extends JpaRepository<Pharmacien, Long> {
    Optional<Pharmacien> findByEmail(String email);
    boolean existsByEmail(String email);

}
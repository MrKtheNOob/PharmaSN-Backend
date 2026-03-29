package com.example.pharmasn.user.repository;

import com.example.pharmasn.user.entity.Pharmacien;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmacienRepository extends JpaRepository<Pharmacien, Long> {
    Optional<Pharmacien> findByNumeroLicence(String numeroLicence);
    boolean existsByNumeroLicence(Long numeroLicence);
}
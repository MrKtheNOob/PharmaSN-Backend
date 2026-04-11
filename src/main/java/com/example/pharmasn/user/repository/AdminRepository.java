package com.example.pharmasn.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmasn.user.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAdminCode(String adminCode);
    boolean existsByAdminCode(String adminCode);
    Optional<Admin> findByEmail(String email);
    boolean existsByEmail(String email);
}

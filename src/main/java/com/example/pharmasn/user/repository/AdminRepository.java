package com.example.pharmasn.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pharmasn.user.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
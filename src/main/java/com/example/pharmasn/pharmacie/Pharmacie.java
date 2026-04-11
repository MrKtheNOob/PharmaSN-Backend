package com.example.pharmasn.pharmacie;

import java.time.LocalDateTime;

import com.example.pharmasn.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

// @Entity
// @Table(name = "pharmacies")
// @Getter
// @Setter
// public class Pharmacie {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(nullable = false, unique = true, length = 100)
//     private String name;

//     @ManyToOne
//     @JoinColumn(name = "owner_id")
//     private User owner;

//     @Column(nullable = false, length = 100)
//     private String address;
    
//     @Column(nullable = false, length = 20)
//     private String phoneNumber;

//     @Column(nullable = false, updatable = false)
//     @Setter(AccessLevel.NONE)
//     private LocalDateTime createdAt;

//     @Column(nullable = false)
//     @Setter(AccessLevel.NONE)
//     private LocalDateTime updatedAt;

//     @PrePersist
//     private void onCreate() {
//         this.createdAt = LocalDateTime.now();
//         this.updatedAt = LocalDateTime.now();
//     }

//     @PreUpdate
//     private void onUpdate() {
//         this.updatedAt = LocalDateTime.now();
//     }
    

// }

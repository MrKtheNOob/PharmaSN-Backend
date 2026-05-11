package com.example.pharmasn.stock;

import com.example.pharmasn.medicament.Medicament;
import com.example.pharmasn.pharmacie.Pharmacie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pharmacie_id", nullable = false)
    private Pharmacie pharmacie;

    @ManyToOne
    @JoinColumn(name = "medicament_id", nullable = false)
    private Medicament medicament;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

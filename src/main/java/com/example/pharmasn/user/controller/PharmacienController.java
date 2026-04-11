package com.example.pharmasn.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmasn.user.entity.Pharmacien;
import com.example.pharmasn.user.repository.PharmacienRepository;
import com.example.pharmasn.user.service.PharmacienService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/pharmaciens")
@AllArgsConstructor
public class PharmacienController {
    private final PharmacienRepository pharmacienRepository;
    private final PharmacienService pharmacienService;

    @GetMapping
    public java.util.List<Pharmacien> getAllPharmaciens() {
        return pharmacienRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacien> getPharmacienById(@PathVariable Long id) {
        return pharmacienRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pharmacien> createPharmacien(@RequestBody Pharmacien pharmacien) {
        return ResponseEntity.status(201).body(pharmacienService.createPharmacien(pharmacien));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacien(@PathVariable Long id) {
        if (pharmacienRepository.existsById(id)) {
            pharmacienRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

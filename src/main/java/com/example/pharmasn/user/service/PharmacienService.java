package com.example.pharmasn.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pharmasn.user.entity.Pharmacien;
import com.example.pharmasn.user.repository.PharmacienRepository;
import com.example.pharmasn.user.exceptions.EmailAlreadyUsedException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PharmacienService {
    private final PharmacienRepository pharmacienRepository;

    public List<Pharmacien> getAllPharmacien(){
        return pharmacienRepository.findAll();
    }
    public Pharmacien getPharmacienById(Long id){
        try {
            Pharmacien pharmacien=pharmacienRepository.findById(id).orElseThrow(() -> new RuntimeException("Pharmacien non trouvé"));
            return pharmacien;
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.getMessage());
            return null;
        }
    }
    public Pharmacien getPharmacienByEmail(String email){
        return pharmacienRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Pharmacien non trouvé"));
    }
    public Pharmacien createPharmacien(Pharmacien pharmacien){
        if (pharmacienRepository.existsByEmail(pharmacien.getEmail())){
            throw new EmailAlreadyUsedException("Cet email est déja utilisé");
        }
        return pharmacienRepository.save(pharmacien);
    }
    public Pharmacien updatePharmacien(Long id, Pharmacien pharmacien){
        Pharmacien existingPharmacien = pharmacienRepository.findById(id).orElse(null);
        if (existingPharmacien == null){
            return null;
        }
        existingPharmacien.setNom(pharmacien.getNom());         
        // existingPharmacien.setPrenom(pharmacien.getPrenom());
        existingPharmacien.setEmail(pharmacien.getEmail());
        // existingPharmacien.setPassword(pharmacien.getPassword());

        return pharmacienRepository.save(existingPharmacien);

    }
    public boolean deletePharmacien(Long id){
        if (!pharmacienRepository.existsById(id)){
            return false;
        }
        pharmacienRepository.deleteById(id);
        return true;
    }
    
}

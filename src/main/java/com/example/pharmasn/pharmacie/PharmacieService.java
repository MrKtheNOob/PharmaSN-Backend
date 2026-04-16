package com.example.pharmasn.pharmacie;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PharmacieService{
    private final PharmacieRepository pharmacieRepository;

    public PharmacieService(PharmacieRepository pharmacieRepository) {
        this.pharmacieRepository = pharmacieRepository;
    }

    public List<Pharmacie> getAllPharmacies(){
        return pharmacieRepository.findAll();
    }
    
    
    public Pharmacie getPharmacieById(Long id){
        return pharmacieRepository.findById(id).orElse(null);
    }
    public Pharmacie createPharmacie(Pharmacie pharmacie){
        return pharmacieRepository.save(pharmacie);
    }
    public Pharmacie updatePharmacie(Long id, Pharmacie pharmacie){
        Pharmacie existingPharmacie = pharmacieRepository.findById(id).orElse(null);
        if (existingPharmacie == null){
            return null;
        }
        existingPharmacie.setName(pharmacie.getName());
        existingPharmacie.setAddress(pharmacie.getAddress());
        return pharmacieRepository.save(existingPharmacie);
    }
    public boolean deletePharmacie(Long id){
        if (!pharmacieRepository.existsById(id)){
            return false;
        }
        pharmacieRepository.deleteById(id);
        return true;
    }
    // search by name
    public List<Pharmacie> searchPharmaciesByName(String name){
        return pharmacieRepository.findByNameContainingIgnoreCase(name);
    }
}
package com.example.pharmasn.pharmacie;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class PharmacieController {
    private final PharmacieService pharmacieService;

    public PharmacieController(PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }
    @GetMapping
    public List<Pharmacie> getAllPharmacies(){
        return pharmacieService.getAllPharmacies();
    }
    @GetMapping("/{id}")
    public Pharmacie getPharmacieById(@PathVariable Long id){
        return pharmacieService.getPharmacieById(id);
    }
    @GetMapping("/search")
    public List<Pharmacie> searchPharmaciesByName(@RequestParam String name){
        return pharmacieService.searchPharmaciesByName(name);
    }
    @PostMapping
    public Pharmacie createPharmacie(@RequestBody Pharmacie pharmacie){
        return pharmacieService.createPharmacie(pharmacie);
    }
    @PutMapping("/{id}")
    public Pharmacie updatePharmacie(@PathVariable Long id, @RequestBody Pharmacie pharmacie){
        return pharmacieService.updatePharmacie(id, pharmacie);
    }
    @DeleteMapping("/{id}")
    public boolean deletePharmacie(@PathVariable Long id){
        return pharmacieService.deletePharmacie(id);
    }
       
}

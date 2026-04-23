package com.example.pharmasn.medicament;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/medicaments")
@AllArgsConstructor
public class MedicamentController {
    private final MedicamentService medicamentService;

    @GetMapping("/")
    public List<Medicament> getAllMedicaments(){
        return medicamentService.getAllMedicaments();
    }
    @GetMapping("/{id}")
    public Medicament getMedicamentById(@PathVariable Long id){
        return medicamentService.getMedicamentById(id);    
    }
    @GetMapping("/search/{name}")
    public List<Medicament> getMedicamentByName(@PathVariable String name){
        return medicamentService.getMedicamentByName(name);
    }
    @PostMapping
    public Medicament createMedicament(@RequestBody Medicament medicament){
        return medicamentService.createMedicament(medicament);
    }

    @PutMapping("/{id}")
    public Medicament updateMedicament(@PathVariable Long id, @RequestBody Medicament medicament){
        medicament.setId(id);
        return medicamentService.updateMedicament(medicament);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicament(@PathVariable Long id){
        medicamentService.deleteMedicament(id);
    }
}

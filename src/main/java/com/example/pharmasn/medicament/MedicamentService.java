package com.example.pharmasn.medicament;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pharmasn.medicament.dtos.MedicamentDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;

    public List<Medicament> getAllMedicaments() {
        return medicamentRepository.findAll();
    }

    public Medicament getMedicamentById(Long id) {
        return medicamentRepository.findById(id)
                .orElseThrow(() -> new MedicamentNotFoundException("Medicament with id " + id + " not found"));
    }

    public List<Medicament> getMedicamentByName(String name) {
        var result = medicamentRepository.findByName(name).orElseThrow(
                () -> new MedicamentNotFoundException("Medicament suivant le mot clé " + name + " non trouvé"));
        return result;
    }

    public Medicament createMedicament(MedicamentDTO medicamentDTO) {
        Medicament medicament = new Medicament();
        medicament.setName(medicamentDTO.getName());
        return medicamentRepository.save(medicament);
    }


    public List<Medicament> searchMedicaments(String query) {
        return medicamentRepository.findByNameContainingIgnoreCase(query);
    }

    public void deleteMedicament(Long id){

        medicamentRepository.deleteById(id);
    }

    public Medicament updateMedicament(Long id, MedicamentDTO medicamentDTO) {
        Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(() -> new MedicamentNotFoundException("Medicament with id " + id + " not found"));
        medicament.setName(medicamentDTO.getName());
        return medicamentRepository.save(medicament);
    }

}

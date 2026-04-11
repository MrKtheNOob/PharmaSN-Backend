package com.example.pharmasn.medicament;

import java.util.List;
import java.util.Optional;

// import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;

    public MedicamentService(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }
    
    public Optional<Medicament> getMedicamentById(Long id){
        return medicamentRepository.findById(id);
    }
    public List<Medicament> getMedicamentByName(String name){
        var result = medicamentRepository.findByName(name).orElseThrow(() -> new MedicamentNotFoundException("Medicament suivant le mot clé "+name+" non trouvé"));
        return result;
    }
    public Medicament createMedicament(Medicament medicament){
        return medicamentRepository.save(medicament);
    }
    
    public void deleteMedicament(Long id){
        medicamentRepository.deleteById(id);
    }

    public Medicament updateMedicament(Medicament medicament){
        return medicamentRepository.save(medicament);
    }
    
}

package com.example.pharmasn.medicament;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicaments")
@RequiredArgsConstructor
public class MedicamentController {

    private final MedicamentService medicamentService;

    @GetMapping
    public List<Medicament> getAllMedicaments() {
        return medicamentService.getAllMedicaments();
    }

    @GetMapping("/search")
    public List<Medicament> searchMedicaments(@RequestParam String query) {
        // We'll add this to service next
        return medicamentService.searchMedicaments(query);
    }
}

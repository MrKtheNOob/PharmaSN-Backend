package com.example.pharmasn.pharmacie;

import com.example.pharmasn.pharmacie.dtos.PharmacieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
@RequiredArgsConstructor
public class PharmacieController {

    private final PharmacieService pharmacieService;

    @GetMapping("/mine")
    public Pharmacie getMine(Authentication authentication) {
        return pharmacieService.getMyPharmacy(authentication.getName());
    }

    @PutMapping("/mine")
    public Pharmacie updateMine(@RequestBody PharmacieDTO dto, Authentication authentication) {
        return pharmacieService.updateMyPharmacy(authentication.getName(), dto);
    }

    @GetMapping
    public List<Pharmacie> getAll() {
        return pharmacieService.getAllPharmacies();
    }
}

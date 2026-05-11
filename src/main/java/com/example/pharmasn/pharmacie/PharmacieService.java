package com.example.pharmasn.pharmacie;

import com.example.pharmasn.pharmacie.dtos.PharmacieDTO;
import com.example.pharmasn.user.entity.User;
import com.example.pharmasn.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PharmacieService {
    private final PharmacieRepository pharmacieRepository;
    private final UserRepository userRepository;

    public Pharmacie getMyPharmacy(String email) {
        return pharmacieRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new PharmacieNotFoundException("Aucune pharmacie trouvée pour cet utilisateur"));
    }

    public Pharmacie updateMyPharmacy(String email, PharmacieDTO dto) {
        Pharmacie pharmacie = getMyPharmacy(email);
        pharmacie.setName(dto.getName());
        pharmacie.setAddress(dto.getAddress());
        pharmacie.setPhoneNumber(dto.getPhoneNumber());
        return pharmacieRepository.save(pharmacie);
    }

    public List<Pharmacie> getAllPharmacies() {
        return pharmacieRepository.findAll();
    }

    public Pharmacie getPharmacieById(Long id) {
        return pharmacieRepository.findById(id)
                .orElseThrow(() -> new PharmacieNotFoundException("Pharmacie with id " + id + " not found"));
    }

    public Pharmacie createPharmacie(PharmacieDTO dto) {
        // Verify owner exists in users table
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new PharmacieNotFoundException("User with id " + dto.getOwnerId() + " not found"));

        Pharmacie pharmacie = new Pharmacie();
        pharmacie.setName(dto.getName());
        pharmacie.setAddress(dto.getAddress());
        pharmacie.setPhoneNumber(dto.getPhoneNumber());
        pharmacie.setOwner(owner);

        return pharmacieRepository.save(pharmacie);
    }

    public Pharmacie updatePharmacie(Long id, Pharmacie pharmacie) {
        Pharmacie existingPharmacie = pharmacieRepository.findById(id).orElse(null);
        if (existingPharmacie == null) {
            return null;
        }
        existingPharmacie.setName(pharmacie.getName());
        existingPharmacie.setAddress(pharmacie.getAddress());
        return pharmacieRepository.save(existingPharmacie);
    }

    public boolean deletePharmacie(Long id) {
        if (!pharmacieRepository.existsById(id)) {
            return false;
        }
        pharmacieRepository.deleteById(id);
        return true;
    }

    // search by name
    public List<Pharmacie> searchPharmaciesByName(String name) {
        return pharmacieRepository.findByNameContainingIgnoreCase(name);
    }
}

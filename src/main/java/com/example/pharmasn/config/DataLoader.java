package com.example.pharmasn.config;

import com.example.pharmasn.medicament.Medicament;
import com.example.pharmasn.medicament.MedicamentRepository;
import com.example.pharmasn.pharmacie.Pharmacie;
import com.example.pharmasn.pharmacie.PharmacieRepository;
import com.example.pharmasn.stock.Stock;
import com.example.pharmasn.stock.StockRepository;
import com.example.pharmasn.user.entity.Role;
import com.example.pharmasn.user.entity.User;
import com.example.pharmasn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PharmacieRepository pharmacieRepository;
    private final MedicamentRepository medicamentRepository;
    private final StockRepository stockRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;

        // 1. Create Pharmacist
        User pharmacien = new User();
        pharmacien.setNom("Diop");
        pharmacien.setPrenom("Moussa");
        pharmacien.setEmail("pharmacien@test.com");
        pharmacien.setPassword(passwordEncoder.encode("password123"));
        pharmacien.setRole(Role.PHARMACIEN);
        pharmacien.setNumeroLicence(776543210L);
        userRepository.save(pharmacien);

        // 2. Create Medicines
        Medicament m1 = createMedicament("Paracétamol 500mg", "Paracétamol", "CIP001");
        Medicament m2 = createMedicament("Ibuprofène 400mg", "Ibuprofène", "CIP002");
        Medicament m3 = createMedicament("Amoxicilline 1g", "Amoxicilline", "CIP003");
        Medicament m4 = createMedicament("Dolirane 1000mg", "Paracétamol", "CIP004");
        Medicament m5 = createMedicament("Spasfon", "Phloroglucinol", "CIP005");

        // 3. Create Pharmacy
        Pharmacie pharmacie = new Pharmacie();
        pharmacie.setName("Grande Pharmacie Dakaroise");
        pharmacie.setAddress("Place de l'Indépendance, Dakar");
        pharmacie.setPhoneNumber("+221 33 823 12 12");
        pharmacie.setOwner(pharmacien);
        pharmacieRepository.save(pharmacie);

        // 4. Create Initial Stock (Prices in FCFA)
        createStock(pharmacie, m1, 150, 1500.0);
        createStock(pharmacie, m2, 85, 3200.0);
        createStock(pharmacie, m3, 12, 5500.0); // Low stock
        createStock(pharmacie, m4, 200, 2000.0);
        createStock(pharmacie, m5, 45, 2800.0);

        System.out.println("--- Database Seeded Successfully with FCFA prices ---");
    }

    private Medicament createMedicament(String name, String active, String cip) {
        Medicament m = new Medicament();
        m.setName(name);
        m.setActivePrinciple(active);
        m.setCipCode(cip);
        return medicamentRepository.save(m);
    }

    private void createStock(Pharmacie p, Medicament m, Integer qty, Double price) {
        Stock s = new Stock();
        s.setPharmacie(p);
        s.setMedicament(m);
        s.setQuantity(qty);
        s.setPrice(price);
        stockRepository.save(s);
    }
}

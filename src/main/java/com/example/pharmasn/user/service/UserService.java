package com.example.pharmasn.user.service;

import com.example.pharmasn.user.entity.Admin;
import com.example.pharmasn.user.entity.Pharmacien;
import com.example.pharmasn.user.entity.User;
import com.example.pharmasn.user.repository.AdminRepository;
import com.example.pharmasn.user.repository.PharmacienRepository;
import com.example.pharmasn.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PharmacienRepository pharmacienRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        if (userRepository.existsByEmail(admin.getEmail())) {
            throw new IllegalArgumentException("Email already used");
        }
        return adminRepository.save(admin);
    }

    @Override
    public Pharmacien createPharmacien(Pharmacien pharmacien) {
        if (userRepository.existsByEmail(pharmacien.getEmail())) {
            throw new IllegalArgumentException("Email already used");
        }

        if (pharmacien.getNumeroLicence() != null
                && pharmacienRepository.existsByNumeroLicence(pharmacien.getNumeroLicence())) {
            throw new IllegalArgumentException("Licence number already used");
        }

        return pharmacienRepository.save(pharmacien);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pharmacien> getAllPharmaciens() {
        return pharmacienRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));

        if (!existingAdmin.getEmail().equals(updatedAdmin.getEmail())
                && userRepository.existsByEmail(updatedAdmin.getEmail())) {
            throw new IllegalArgumentException("Email already used");
        }

        existingAdmin.setNom(updatedAdmin.getNom());
        existingAdmin.setPrenom(updatedAdmin.getPrenom());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        existingAdmin.setPassword(updatedAdmin.getPassword());
        existingAdmin.setAdminCode(updatedAdmin.getAdminCode());

        return adminRepository.save(existingAdmin);
    }

    @Override
    public Pharmacien updatePharmacien(Long id, Pharmacien updatedPharmacien) {
        Pharmacien existingPharmacien = pharmacienRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pharmacien not found with id: " + id));

        if (!existingPharmacien.getEmail().equals(updatedPharmacien.getEmail())
                && userRepository.existsByEmail(updatedPharmacien.getEmail())) {
            throw new IllegalArgumentException("Email already used");
        }

        if (updatedPharmacien.getNumeroLicence() != null
                && !updatedPharmacien.getNumeroLicence().equals(existingPharmacien.getNumeroLicence())
                && pharmacienRepository.existsByNumeroLicence(updatedPharmacien.getNumeroLicence())) {
            throw new IllegalArgumentException("Licence number already used");
        }

        existingPharmacien.setNom(updatedPharmacien.getNom());
        existingPharmacien.setPrenom(updatedPharmacien.getPrenom());
        existingPharmacien.setEmail(updatedPharmacien.getEmail());
        existingPharmacien.setPassword(updatedPharmacien.getPassword());
        existingPharmacien.setNumeroLicence(updatedPharmacien.getNumeroLicence());
        existingPharmacien.setNomPharmacie(updatedPharmacien.getNomPharmacie());

        return pharmacienRepository.save(existingPharmacien);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
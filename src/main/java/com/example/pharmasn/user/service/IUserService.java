package com.example.pharmasn.user.service;

import com.example.pharmasn.user.entity.Admin;
import com.example.pharmasn.user.entity.Pharmacien;
import com.example.pharmasn.user.entity.User;

import java.util.List;

public interface IUserService {
    Admin createAdmin(Admin admin);
    Pharmacien createPharmacien(Pharmacien pharmacien);

    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);

    List<Admin> getAllAdmins();
    List<Pharmacien> getAllPharmaciens();

    Admin updateAdmin(Long id, Admin updatedAdmin);
    Pharmacien updatePharmacien(Long id, Pharmacien updatedPharmacien);

    void deleteUser(Long id);
}
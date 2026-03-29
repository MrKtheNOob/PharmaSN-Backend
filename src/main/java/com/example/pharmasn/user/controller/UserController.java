package com.example.pharmasn.user.controller;

import com.example.pharmasn.user.entity.Admin;
import com.example.pharmasn.user.entity.Pharmacien;
import com.example.pharmasn.user.entity.User;
import com.example.pharmasn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAdmin(admin));
    }

    @PostMapping("/pharmaciens")
    public ResponseEntity<Pharmacien> createPharmacien(@RequestBody Pharmacien pharmacien) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createPharmacien(pharmacien));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }

    @GetMapping("/pharmaciens")
    public ResponseEntity<List<Pharmacien>> getAllPharmaciens() {
        return ResponseEntity.ok(userService.getAllPharmaciens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return ResponseEntity.ok(userService.updateAdmin(id, admin));
    }

    @PutMapping("/pharmaciens/{id}")
    public ResponseEntity<Pharmacien> updatePharmacien(@PathVariable Long id, @RequestBody Pharmacien pharmacien) {
        return ResponseEntity.ok(userService.updatePharmacien(id, pharmacien));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
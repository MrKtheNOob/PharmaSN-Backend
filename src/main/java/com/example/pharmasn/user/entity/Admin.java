package com.example.pharmasn.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor

public class Admin extends User {

    @Column(length = 100)
    private String adminCode;

    @PrePersist
    @PreUpdate
    public void ensureRole() {
        assignRole(Role.ADMIN);
    }
}
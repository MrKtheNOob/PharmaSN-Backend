package com.example.pharmasn.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "pharmaciens")
@Getter
@Setter
@NoArgsConstructor

public class Pharmacien extends User {
    @Column
    private Long numeroLicence;
}
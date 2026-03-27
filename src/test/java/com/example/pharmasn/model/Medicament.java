package com.example.pharmasn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "medicament")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true,length = 13)
    @NotBlank(message = "le code CIP est obligatoire")
    @Size(min = 7,max = 13,message = "le code CIP doit avoir 7 et 13 caractéres")
    private String codeCip;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "le nom commercial est obligatoie")
    private String nomCommercial;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "le principe actif est obligatoire")
    private String principeActif;
    @Column(nullable = false,length = 150)
    @Positive(message = "le prix doit obligatoire")
    private String fabricant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "le code CIP est obligatoire") @Size(min = 7, max = 13, message = "le code CIP doit avoir 7 et 13 caractéres") String getCodeCip() {
        return codeCip;
    }

    public void setCodeCip(@NotBlank(message = "le code CIP est obligatoire") @Size(min = 7, max = 13, message = "le code CIP doit avoir 7 et 13 caractéres") String codeCip) {
        this.codeCip = codeCip;
    }

    public @NotBlank(message = "le nom commercial est obligatoie") String getNomCommercial() {
        return nomCommercial;
    }

    public void setNomCommercial(@NotBlank(message = "le nom commercial est obligatoie") String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public @NotBlank(message = "le principe actif est obligatoire") String getPrincipeActif() {
        return principeActif;
    }

    public void setPrincipeActif(@NotBlank(message = "le principe actif est obligatoire") String principeActif) {
        this.principeActif = principeActif;
    }

    public @Positive(message = "le prix doit obligatoire") String getFabricant() {
        return fabricant;
    }

    public void setFabricant(@Positive(message = "le prix doit obligatoire") String fabricant) {
        this.fabricant = fabricant;
    }
}

package com.example.pharmasn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "pharmaciens")
@PrimaryKeyJoinColumn(name = "user_id")
public class Pharmacien extends User{
    @Column(nullable = false,unique = true)
    private String numeroLicence;
    @Column(nullable = false)
    private  String nomPharmacie;
    @Column(nullable = false)
    private  String adressePharmacie;

    public String getNumeroLicence() {
        return numeroLicence;
    }

    public void setNumeroLicence(String numeroLicence) {
        this.numeroLicence = numeroLicence;
    }

    public String getNomPharmacie() {
        return nomPharmacie;
    }

    public void setNomPharmacie(String nomPharmacie) {
        this.nomPharmacie = nomPharmacie;
    }

    public String getAdressePharmacie() {
        return adressePharmacie;
    }

    public void setAdressePharmacie(String adressePharmacie) {
        this.adressePharmacie = adressePharmacie;
    }
}

package com.devweb.trans.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(exclude = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String adresse;
    @Column(length = 50)
    private String ninea;
    @Column(length = 50)
    private String raisonsociale;

    private String status;

    @Column(length = 50)
    private String phone;
    private String emailentr;

    public Entreprise(String adresse,String ninea, String raisonsociale, String status,  String phone, String emailentr ) {
        this.adresse = adresse;
        this.ninea = ninea;
        this.raisonsociale = raisonsociale;
        this.status = status;
        this.phone = phone;
        this.emailentr = emailentr;

    }

    public String getEmailentr() {
        return emailentr;
    }

    public void setEmailentr(String emailentr) {
        this.emailentr = emailentr;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaisonsociale() {
        return raisonsociale;
    }

    public void setRaisonsociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

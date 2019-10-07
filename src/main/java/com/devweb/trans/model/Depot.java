package com.devweb.trans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table
@Data
@EqualsAndHashCode(exclude = "depot")
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long montant;
    private Date date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn

    private Compte compte;
    @JsonIgnore

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn

    private User caissier;

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public User getCaissier() {
        return caissier;
    }

    public void setCaissier(User caissier) {
        this.caissier = caissier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

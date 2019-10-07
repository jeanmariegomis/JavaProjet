package com.devweb.trans.Form;

public class DepotForm {
    private String compte;
    private long montant;
    private String numerocompte;
    private String solde;

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public String getNumerocompte() {
        return numerocompte;
    }

    public void setNumerocompte(String numerocompte) {
        this.numerocompte = numerocompte;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
}

package com.devweb.trans.model;

public class LireAjouter {
    private int status;
    private String lire;
    private String ajouter;

    public LireAjouter(int status, String lire, String ajouter){
        this.status = status;
        this.lire = lire;
        this.ajouter = ajouter;
    }

    public LireAjouter(int Status, String msg) {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLire() {
        return lire;
    }

    public void setLire(String lire) {
        this.lire = lire;
    }

    public String getAjouter() {
        return ajouter;
    }

    public void setAjouter(String ajouter) {
        this.ajouter = ajouter;
    }
}

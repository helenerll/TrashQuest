package com.example.trashquest.db;

public class Commune {

    int codePostal;
    String nom;
    int ramassageBlanche;
    int ramassageBlancheBis;
    int ramassageBleue;
    int ramassageJaune;
    int ramassageVert;
    int ramassageOrange;

    public Commune() {

    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getRamassageBleue() {
        return ramassageBleue;
    }

    public void setRamassageBleue(int ramassageBleue) {
        this.ramassageBleue = ramassageBleue;
    }

    public int getRamassageJaune() {
        return ramassageJaune;
    }

    public void setRamassageJaune(int ramassageJaune) {
        this.ramassageJaune = ramassageJaune;
    }

    public int getRamassageBlanche() {
        return ramassageBlanche;
    }

    public void setRamassageBlanche(int ramassageBlanche) {
        this.ramassageBlanche = ramassageBlanche;
    }

    public int getRamassageVert() {
        return ramassageVert;
    }

    public void setRamassageVert(int ramassageVert) {
        this.ramassageVert = ramassageVert;
    }

    public int getRamassageOrange() {
        return ramassageOrange;
    }

    public void setRamassageOrange(int ramassageOrange) {
        this.ramassageOrange = ramassageOrange;
    }

    public int getRamassageBlancheBis() {
        return ramassageBlancheBis;
    }

    public void setRamassageBlancheBis(int ramassageBlancheBis) {
        this.ramassageBlancheBis = ramassageBlancheBis;
    }

    @Override
    public String toString() {
        return "Commune{" +
                "codePostal=" + codePostal +
                ", nom='" + nom + '\'' +
                ", ramassageBlanche=" + ramassageBlanche +
                ", ramassageBlancheBis=" + ramassageBlancheBis +
                ", ramassageBleue=" + ramassageBleue +
                ", ramassageJaune=" + ramassageJaune +
                ", ramassageVert=" + ramassageVert +
                ", ramassageOrange=" + ramassageOrange +
                '}';
    }


}

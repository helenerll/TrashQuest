package com.example.trashquest.db;

public class Calendrier {

    int id;
    int codePostal;
    int ramassageBleue;
    int ramassageJaune;
    int ramassageBlanche;
    int ramassageVert;
    int ramassageOrange;

    public Calendrier() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
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
}

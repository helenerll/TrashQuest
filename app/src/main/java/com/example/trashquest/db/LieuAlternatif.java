package com.example.trashquest.db;

import android.os.Parcel;
import android.os.Parcelable;

public class LieuAlternatif implements Parcelable {

    int id;
    String nom;
    String commune;
    String codePostal;
    String adresse;
    String typeLieu;

    public LieuAlternatif() {

    }

    protected LieuAlternatif(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        commune = in.readString();
        codePostal = in.readString();
        adresse = in.readString();
        typeLieu = in.readString();
    }

    public static final Creator<LieuAlternatif> CREATOR = new Creator<LieuAlternatif>() {
        @Override
        public LieuAlternatif createFromParcel(Parcel in) {
            return new LieuAlternatif(in);
        }

        @Override
        public LieuAlternatif[] newArray(int size) {
            return new LieuAlternatif[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeLieu() {
        return typeLieu;
    }

    public void setTypeLieu(String typeLieu) {
        this.typeLieu = typeLieu;
    }

    @Override
    public String toString() {
        return "LieuAlternatif{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", commune='" + commune + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", adresse='" + adresse + '\'' +
                ", typeLieu='" + typeLieu + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(codePostal);
        dest.writeString(adresse);
        dest.writeString(typeLieu);

    }
}

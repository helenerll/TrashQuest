package com.example.trashquest.db;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TypeDechet implements Parcelable {

    int id;
    String nom;
    String commentaire;
    String idPoubelle;


    public TypeDechet() {

    }

    public TypeDechet(int id, String nom, String commentaire, String idPoubelle) {
        this.id = id;
        this.nom = nom;
        this.commentaire = commentaire;
        this.idPoubelle = idPoubelle;
    }

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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getIdPoubelle() {
        return idPoubelle;
    }

    public void setIdPoubelle(String idPoubelle) {
        this.idPoubelle = idPoubelle;
    }

    @Override
    public String toString() {
        return "TypeDechet{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", idPoubelle=" + idPoubelle +
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
        dest.writeString(commentaire);
        dest.writeString(idPoubelle);

    }

    public TypeDechet(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        commentaire = in.readString();
        idPoubelle = in.readString();
    }

    public static final Parcelable.Creator<TypeDechet> CREATOR = new Creator<TypeDechet>() {
        @Override
        public TypeDechet createFromParcel(Parcel source) {
            return new TypeDechet(source);
        }

        @Override
        public TypeDechet[] newArray(int size) {
            return new TypeDechet[size];
        }
    };
}


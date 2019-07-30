package com.example.trashquest.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Poubelle implements Parcelable {

    String id;
    int idTypeDechet;

    public Poubelle() {

    }

    protected Poubelle(Parcel in) {
        id = in.readString();
        idTypeDechet = in.readInt();
    }

    public static final Creator<Poubelle> CREATOR = new Creator<Poubelle>() {
        @Override
        public Poubelle createFromParcel(Parcel in) {
            return new Poubelle(in);
        }

        @Override
        public Poubelle[] newArray(int size) {
            return new Poubelle[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdTypeDechet() {
        return idTypeDechet;
    }

    public void setIdTypeDechet(int idTypeDechet) {
        this.idTypeDechet = idTypeDechet;
    }

    @Override
    public String toString() {
        return "Poubelle{" +
                "id='" + id + '\'' +
                ", idTypeDechet=" + idTypeDechet +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(idTypeDechet);
    }
}

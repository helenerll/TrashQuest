package com.example.trashquest.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Usager implements Parcelable {

    private String login;
    int codePostal;

    public Usager() {

    }

    public static final Creator<Usager> CREATOR = new Creator<Usager>() {
        @Override
        public Usager createFromParcel(Parcel in) {
            return new Usager(in);
        }

        @Override
        public Usager[] newArray(int size) {
            return new Usager[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public Usager(String login, int codePostal) {
        this.login = login;
        this.codePostal = codePostal;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(codePostal);
    }

    public Usager(Parcel in) {
        login = in.readString();
        codePostal = in.readInt();
    }
}

package com.example.trashquest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static int DATABASE_VERSION = 6 ;

    private static final String TABLE_ARTICLE = "article";
    private static final String TABLE_AUTEUR = "auteur";
    private static final String TABLE_CALENDRIER = "calendrier";
    private static final String TABLE_COMMUNE = "commune";
    private static final String TABLE_ALTER = "alternatif";
    private static final String TABLE_NOTE = "note";
    private static final String TABLE_POUBELLE = "poubelle";
    private static final String TABLE_PRODUIT = "produit";
    private static final String TABLE_TYPE = "type";
    private static final String TABLE_USAGER = "usager";

    //colonnes table article
    private static final String ID_ARTICLE = "ID_ARTICLE";
    private static final String THEMATIQUE = "THEMATIQUE";
    private static final String ARTICLE_AUTEUR = "ARTICLE_AUTEUR";

    //colonnes table auteur
    private static final String ID_AUTEUR = "ID_AUTEUR";
    private static final String AUTEUR = "AUTEUR";

    //colonnes table calendrier
    public static final String ID_CALENDRIER = "ID_CALENDRIER";
    public static final String CALENDRIER_CODE_POSTAL = "CALENDRIER_CODE_POSTAL";
    public static final String CALENDRIER_RAMASSAGE_BLEUE = "CALENDRIER_RAMASSAGE_BLEUE";
    public static final String CALENDRIER_RAMASSAGE_JAUNE = "CALENDRIER_RAMASSAGE_JAUNE";
    public static final String CALENDRIER_RAMASSAGE_BLANCHE = "CALENDRIER_RAMASSAGE_BLANCHE";
    public static final String CALENDRIER_RAMASSAGE_VERTE = "CALENDRIER_RAMASSAGE_VERTE";
    public static final String CALENDRIER_RAMASSAGE_ORANGE = "CALENDRIER_RAMASSAGE_ORANGE";


    //colonnes table commune
    //blanche 1 / blanche 2 / bleue / jaune / verte / orange
    public static final String CODE_POSTAL = "CODE_POSTAL";
    public static final String COMMUNE_NOM = "COMMUNE_NOM";
    public static final String RAMASSAGE_BLANCHE = "RAMASSAGE_BLANCHE";
    public static final String RAMASSAGE_BLANCHE_BIS = "RAMASSAGE_BLANCHE_BIS";
    public static final String RAMASSAGE_BLEUE = "RAMASSAGE_BLEUE";
    public static final String RAMASSAGE_JAUNE = "RAMASSAGE_JAUNE";
    public static final String RAMASSAGE_VERTE = "RAMASSAGE_VERTE";
    public static final String RAMASSAGE_ORANGE = "RAMASSAGE_ORANGE";

    //colonnes table lieu alternatif
    public static final String _id = "_id";
    public static final String TYPE_LIEU = "TYPE_LIEU";
    public static final String LIEU_CODE_POSTAL = "LIEU_CODE_POSTAL";
    public static final String LIEU_COMMUNE = "LIEU_COMMUNE";
    public static final String ADRESSE = "ADRESSE";
    public static final String LIEU_NOM = "LIEU_NOM";

    //colones table note
    public static final String ID_NOTE ="ID_NOTE";
    public static final String NOTE_AUTEUR = "NOTE_AUTEUR";

    //colonnes table poubelle
    public static final String ID_POUBELLE = "ID_POUBELLE";
    public static final String POUBELLE_TYPE = "POUBELLE_TYPE";

    //colonnes tables produit
    public static final String ID_PRODUIT = "ID_PRODUIT";
    public static final String PRODUIT_NOM = "PRODUIT_NOM";


    //colonnes table type
    public static final String ID_TYPE = "ID_TYPE";
    public static final String TYPE_NOM = "TYPE_NOM";
    public static final String TYPE_POUBELLE = "TYPE_POUBELLE";
    public static final String COMMENTAIRE = "COMMENTAIRE";

    //colonnes table usager
    public static final String LOGIN = "LOGIN";
    public static final String USAGER_CODE_POSTAL = "USAGER_CODE_POSTAL";

    //create table article
    public static final String CREATE_TABLE_ARTICLE = "CREATE TABLE "
            + TABLE_ARTICLE + " (" + ID_ARTICLE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + THEMATIQUE + " TEXT, " + ARTICLE_AUTEUR + " TEXT);";

    //create table auteur
    public static final String CREATE_TABLE_AUTEUR = "CREATE TABLE "
            + TABLE_AUTEUR + " (" + ID_AUTEUR + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AUTEUR + "TEXT);";

    //create table calendrier
    public static final String CREATE_TABLE_CALENDRIER = "CREATE TABLE "
            + TABLE_CALENDRIER + " (" + ID_CALENDRIER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CALENDRIER_CODE_POSTAL + " INTEGER, "
            + CALENDRIER_RAMASSAGE_BLEUE + " INTEGER, "
            + CALENDRIER_RAMASSAGE_JAUNE + " INTEGER, "
            + CALENDRIER_RAMASSAGE_BLANCHE + " INTEGER, "
            + CALENDRIER_RAMASSAGE_VERTE + " INTEGER, "
            + CALENDRIER_RAMASSAGE_ORANGE + " INTEGER);";

    //create table commune
    public static final String CREATE_TABLE_COMMUNE = "CREATE TABLE "
            + TABLE_COMMUNE + " (" + CODE_POSTAL + " INTEGER PRIMARY KEY, "
            + COMMUNE_NOM + " TEXT, "
            + RAMASSAGE_BLANCHE + " INTEGER, "
            + RAMASSAGE_BLANCHE_BIS + " INTEGER, "
            + RAMASSAGE_BLEUE + " INTEGER, "
            + RAMASSAGE_JAUNE + " INTEGER, "
            + RAMASSAGE_VERTE + " INTEGER, "
            + RAMASSAGE_ORANGE + " INTEGER);";

    //create table lieu alternatif
    public static final String CREATE_TABLE_ALTER = "CREATE TABLE "
            + TABLE_ALTER + " (" + _id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TYPE_LIEU + " TEXT, "
            + LIEU_CODE_POSTAL + " INTEGER, "
            + LIEU_COMMUNE + " TEXT, "
            + ADRESSE + " TEXT, "
            + LIEU_NOM + " TEXT);";

    //create table note
    public static final String CREATE_TABLE_NOTE = "CREATE TABLE "
            + TABLE_NOTE + " ("
            + ID_NOTE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NOTE_AUTEUR + " TEXT);";

    //create table poubelle
    public static final String CREATE_TABLE_POUBELLE = "CREATE TABLE "
            + TABLE_POUBELLE + " (" + ID_POUBELLE + " TEXT PRIMARY KEY, "
            + POUBELLE_TYPE + " INTEGER," + " FOREIGN KEY (" + POUBELLE_TYPE+") REFERENCES " + TABLE_TYPE + " ("+ID_TYPE+"));";

    //create table produit
    public static final String CREATE_TABLE_PRODUIT = "CREATE TABLE "
            + TABLE_PRODUIT + " (" + ID_PRODUIT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRODUIT_NOM + " TEXT);";

    //create table type
    public static final String CREATE_TABLE_TYPE = "CREATE TABLE "
            + TABLE_TYPE + " (" + ID_TYPE + " INTEGER PRIMARY KEY, "
            + TYPE_NOM + " TEXT, "
            + COMMENTAIRE + " TEXT, "
            + TYPE_POUBELLE + " TEXT, " + " FOREIGN KEY (" + TYPE_POUBELLE + ") REFERENCES " + TABLE_POUBELLE + " (" +ID_POUBELLE+"));";

    //+ TASK_CAT + " integer,"
    //        + " FOREIGN KEY ("+TASK_CAT+") REFERENCES "+CAT_TABLE+"("+CAT_ID+"));";

    //create table usager
    public static final String CREATE_TABLE_USAGER = "CREATE TABLE "
            + TABLE_USAGER + " (" + LOGIN + " TEXT PRIMARY KEY, "
            + USAGER_CODE_POSTAL + " INTEGER);";


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALTER);
        db.execSQL(CREATE_TABLE_ARTICLE);
        db.execSQL(CREATE_TABLE_AUTEUR);
        db.execSQL(CREATE_TABLE_CALENDRIER);
        db.execSQL(CREATE_TABLE_COMMUNE);
        db.execSQL(CREATE_TABLE_NOTE);
        db.execSQL(CREATE_TABLE_POUBELLE);
        db.execSQL(CREATE_TABLE_PRODUIT);
        db.execSQL(CREATE_TABLE_TYPE);
        db.execSQL(CREATE_TABLE_USAGER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USAGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTEUR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDRIER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMUNE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POUBELLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);

        onCreate(db);

    }


}

package com.example.trashquest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.trashquest.R;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

    public class LieuAlternatifDAO {

    private static final int VERSION_DB = 5;
    private static final String NOM_DB = "alternatif.db";

    private static final String TABLE_NAME = "alternatif";
    private static final String _id = "_id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_CP = "LIEU_CODE_POSTAL";
    private static final int NUM_COL_CP = 1;
    private static final String COL_VILLE = "LIEU_COMMUNE";
    private static final int NUM_COL_VILLE = 2;
    private static final String COL_RUE = "ADRESSE";
    private static final int NUM_COL_RUE = 3;
    private static final String COL_TYPE_LIEU = "TYPE_LIEU";
    private static final int NUM_COL_TYPE_LIEU = 4;
    private static final String COL_NOM_LIEU = "LIEU_NOM";
    private static final int NUM_COL_NOM_LIEU = 5;


//    private final static String RESOURCES_PATH = "src/main/res/raw/";
//    private final static String FILE_NAME = "lieualternatif";
    private final static char SEPARATOR = ',';

    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DbHelper dbHelper;

    public LieuAlternatifDAO(Context context) {
        dbHelper = new DbHelper(context, NOM_DB, null, VERSION_DB);
        this.context = context;
    }

    public void open() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
    }

    public SQLiteDatabase getDb() {
        return sqLiteDatabase;
    }


    public void populateWithCSV () {
       // File file = new File(RESOURCES_PATH + FILE_NAME);
        try {
          //  FileReader fileReader = new FileReader(file);
            CSVParser csvParser = new CSVParserBuilder().withSeparator(SEPARATOR).build();
            CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(context.getResources()
                    .openRawResource(R.raw.lieualternatif))).withSkipLines(1).withCSVParser(csvParser).build();

            String[] data;
            while ((data = csvReader.readNext()) != null)
            {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COL_CP, data[0]);
                contentValues.put(COL_VILLE, data[1]);
                contentValues.put(COL_RUE, data[2]);
                contentValues.put(COL_TYPE_LIEU, data[3]);
                contentValues.put(COL_NOM_LIEU, data[4]);
                sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LieuAlternatif> cursorToLieuAlternatif (Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        } else {
            ArrayList<LieuAlternatif> maListe = new ArrayList<LieuAlternatif>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                LieuAlternatif lieuAlternatif = new LieuAlternatif();
                lieuAlternatif.setId(cursor.getInt(NUM_COL_ID));
                lieuAlternatif.setCodePostal(cursor.getString(NUM_COL_CP));
                lieuAlternatif.setCommune(cursor.getString(NUM_COL_VILLE));
                lieuAlternatif.setAdresse(cursor.getString(NUM_COL_RUE));
                lieuAlternatif.setTypeLieu(cursor.getString(NUM_COL_TYPE_LIEU));
                lieuAlternatif.setNom(cursor.getString(NUM_COL_NOM_LIEU));
                maListe.add(lieuAlternatif);
                cursor.moveToNext();
            }
            cursor.close();
            return maListe;
        }
    }

    public ArrayList<LieuAlternatif> getLieuAlternatifWithCP(String codePostal) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {_id, COL_CP, COL_VILLE, COL_RUE, COL_TYPE_LIEU, COL_NOM_LIEU}, COL_CP
                + " LIKE \"" + codePostal + "\"", null, null, null, null);
        return cursorToLieuAlternatif(cursor);

    }

    public ArrayList<LieuAlternatif> getOliobox (String codePostal) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {_id, COL_CP, COL_VILLE, COL_RUE, COL_TYPE_LIEU, COL_NOM_LIEU}, COL_TYPE_LIEU
                + " LIKE \"" + "Oliobox" + "\"" + " AND " + COL_CP + " LIKE \"" + codePostal + "\"", null, null, null, null);
        return cursorToLieuAlternatif(cursor);
    }

    public ArrayList<LieuAlternatif> getRecupPilles (String codePostal) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {_id, COL_CP, COL_VILLE, COL_RUE, COL_TYPE_LIEU, COL_NOM_LIEU}, COL_TYPE_LIEU
                + " LIKE \"" + "Récupération piles " + "\"" + " AND " + COL_CP + " LIKE \"" + codePostal + "\"", null, null, null, null);
        return cursorToLieuAlternatif(cursor);
    }

    public ArrayList<LieuAlternatif> getRecupAmpoules (String codePostal) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {_id, COL_CP, COL_VILLE, COL_RUE, COL_TYPE_LIEU, COL_NOM_LIEU}, COL_TYPE_LIEU
                + " LIKE \"" + "Récupération ampoules" + "\"" + " AND " + COL_CP + " LIKE \"" + codePostal + "\"", null, null, null, null);
        return cursorToLieuAlternatif(cursor);
    }

    public ArrayList<LieuAlternatif> getRecupTextile (String codePostal) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {_id, COL_CP, COL_VILLE, COL_RUE, COL_TYPE_LIEU, COL_NOM_LIEU}, COL_TYPE_LIEU
                + " LIKE \"" + "Récupération textile" + "\"" + " AND " + COL_CP + " LIKE \"" + codePostal + "\"", null, null, null, null);
        return cursorToLieuAlternatif(cursor);
    }

    public ArrayList<LieuAlternatif> getAllParcConteneur () {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {_id, COL_CP, COL_VILLE, COL_RUE, COL_TYPE_LIEU, COL_NOM_LIEU}, COL_TYPE_LIEU
                + " LIKE \"" + "Parc conteneur" + "\"", null, null, null, null);
        return cursorToLieuAlternatif(cursor);
    }

    public ArrayList<LieuAlternatif> getAllDeadAnimals () {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {_id, COL_CP, COL_VILLE, COL_RUE, COL_TYPE_LIEU, COL_NOM_LIEU}, COL_TYPE_LIEU
        + " LIKE \"" + "Cadavres" + "\"", null, null, null, null);
        return cursorToLieuAlternatif(cursor);
    }

    public long countRow() {
        long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_NAME);
        return count;
    }

}

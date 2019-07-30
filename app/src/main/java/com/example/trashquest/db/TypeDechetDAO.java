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

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TypeDechetDAO {

    private static final int VERSION_DB = 2;
    private static final String NOM_DB = "type.db";

    private static final String TABLE_NAME = "type";
    private static final String COL_ID = "ID_TYPE";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "TYPE_NOM";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_COMMENTAIRE = "COMMENTAIRE";
    private static final int NUM_COL_COMMENTAIRE = 2;
    private static final String COL_POUBELLE = "TYPE_POUBELLE";
    private static final int NUM_COL_POUBELLE = 3;


    private final static char SEPARATOR = ',';

    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DbHelper dbHelper;

    public TypeDechetDAO(Context context) {
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

    public void populateFromCSV() throws IOException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(SEPARATOR).build();
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(context.getResources()
                .openRawResource(R.raw.typedechet)))
                .withSkipLines(1)
                .withCSVParser(csvParser)
                .build();

        String[] data;
        while ((data = csvReader.readNext()) != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_ID, data[0]);
            contentValues.put(COL_NOM, data[1]);
            contentValues.put(COL_COMMENTAIRE, data[2]);
            contentValues.put(COL_POUBELLE, data[3]);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<TypeDechet> getAllTypeDechet() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {COL_ID, COL_NOM, COL_POUBELLE, COL_COMMENTAIRE}, null, null, null, null, null);
        return cursorToTypeDechet(cursor);
    }

    public TypeDechet getTypeDechetWithId (Integer id) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {COL_ID, COL_NOM, COL_POUBELLE, COL_COMMENTAIRE}, COL_ID
                + " LIKE \"" + id + "\"", null, null, null, null);
        return cursorToSimpleTypeDechet(cursor);
    }

    private ArrayList<TypeDechet> cursorToTypeDechet(Cursor cursor) {
        if (cursor.getCount() == 0) return null;
        else {
            ArrayList<TypeDechet> maListe = new ArrayList<TypeDechet>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                TypeDechet typeDechet = new TypeDechet();
                typeDechet.setId(cursor.getInt(NUM_COL_ID));
                typeDechet.setNom(cursor.getString(NUM_COL_NOM));
                typeDechet.setCommentaire(cursor.getString(NUM_COL_COMMENTAIRE));
                typeDechet.setIdPoubelle(cursor.getString(NUM_COL_POUBELLE));
                maListe.add(typeDechet);
                cursor.moveToNext();
            }
            cursor.close();
            return maListe;
        }
    }

    private TypeDechet cursorToSimpleTypeDechet(Cursor cursor) {
        if (cursor.getCount() == 0) return null;
        else {
            TypeDechet typeDechet = new TypeDechet();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                typeDechet.setId(cursor.getInt(NUM_COL_ID));
                typeDechet.setNom(cursor.getString(NUM_COL_NOM));
                typeDechet.setCommentaire(cursor.getString(NUM_COL_COMMENTAIRE));
                typeDechet.setIdPoubelle(cursor.getString(NUM_COL_POUBELLE));
            }
            cursor.close();
            return typeDechet;
        }
    }

    public long countRow() {
        long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_NAME);
        return count;
    }

}


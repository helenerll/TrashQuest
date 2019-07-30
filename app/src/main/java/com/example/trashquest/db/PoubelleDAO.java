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

public class PoubelleDAO {

    private static final int VERSION_DB = 1;
    private static final String NOM_DB = "poubelle.db";

    private static final String TABLE_NAME = "poubelle";
    private static final String COL_ID = "ID_POUBELLE";
    private static final int NUM_COL_ID = 0;
    private static final String COL_TYPE = "POUBELLE_TYPE";
    private static final int NUM_COL_TYPE = 1;

    private static final char SEPARATOR = ',';

    private SQLiteDatabase sqLiteDatabase;
    private DbHelper dbHelper;
    private Context context;

    public PoubelleDAO(Context context) {
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

    public void populatePoubelleWithCSV() throws IOException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(SEPARATOR).build();
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(context.getResources()
                .openRawResource(R.raw.poubelles)))
                .withSkipLines(1)
                .withCSVParser(csvParser)
                .build();

        String[] data;
        while ((data = csvReader.readNext()) != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_ID, data[0]);
            contentValues.put(COL_TYPE, data[1]);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<Poubelle> getAllPoubelle() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new  String[] {COL_ID, COL_TYPE}, null, null, null, null, null);
        return cursorToPoubelle(cursor);
    }

    private ArrayList<Poubelle> cursorToPoubelle(Cursor cursor) {
        if (cursor.getCount() == 0) return null;
        else {
            ArrayList<Poubelle> maListe = new ArrayList<Poubelle>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Poubelle poubelle = new Poubelle();
                poubelle.setId(cursor.getString(NUM_COL_ID));
                poubelle.setIdTypeDechet(cursor.getInt(NUM_COL_TYPE));
                maListe.add(poubelle);
                cursor.moveToNext();
            }
            cursor.close();
            return maListe;
        }
    }

    public long countRow() {
        long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_NAME);
        return count;
    }

}

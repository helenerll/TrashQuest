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

public class CommuneDAO {

    private static final int VERSION_DB = 1;
    private static final String NOM_DB = "commune.db";

    private static final String TABLE_NAME = "commune";
    private static final String COL_CP = "CODE_POSTAL";
    private static final int NUM_COL_CP = 0;
    private static final String COL_NOM = "COMMUNE_NOM";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_RAM_BLANCHE = "RAMASSAGE_BLANCHE";
    private static final int NUM_COL_RAM_BLANCHE = 2;
    private static final String COL_RAM_BLANCHE_BIS = "RAMASSAGE_BLANCHE_BIS";
    private static final int NUM_COL_RAM_BLANCHE_BIS = 3;
    private static final String COL_RAM_BLEUE = "RAMASSAGE_BLEUE";
    private static final int NUM_COL_RAM_BLEUE = 4;
    private static final String COL_RAM_JAUNE = "RAMASSAGE_JAUNE";
    private static final int NUM_COL_RAM_JAUNE = 5;
    private static final String COL_RAM_VERTE = "RAMASSAGE_VERTE";
    private static final int NUM_COL_RAM_VERTE = 6;
    private static final String COL_RAM_ORANGE = "RAMASSAGE_ORANGE";
    private static final int NUM_COL_RAM_ORANGE = 7;

    private static final char SEPARATOR = ',';

    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DbHelper dbHelper;

    public CommuneDAO(Context context) {
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

    public void populateCommuneWithCSV() throws IOException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(SEPARATOR).build();
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(context.getResources().openRawResource(R.raw.communes)))
                .withSkipLines(1)
                .withCSVParser(csvParser)
                .build();

        String[] data;
        while ((data = csvReader.readNext()) != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_CP, data[0]);
            contentValues.put(COL_NOM, data[1]);
            contentValues.put(COL_RAM_BLANCHE, data[2]);
            contentValues.put(COL_RAM_BLANCHE_BIS, data[3]);
            contentValues.put(COL_RAM_BLEUE, data[4]);
            contentValues.put(COL_RAM_JAUNE, data[5]);
            contentValues.put(COL_RAM_VERTE, data[6]);
            contentValues.put(COL_RAM_ORANGE, data[7]);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }
    }

    public Commune getCommuneWithCP(Integer codePostal) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {COL_CP, COL_NOM, COL_RAM_BLANCHE, COL_RAM_BLANCHE_BIS, COL_RAM_BLEUE, COL_RAM_JAUNE, COL_RAM_VERTE, COL_RAM_ORANGE},
                COL_CP + " LIKE \""  + codePostal + "\"", null, null, null, null);
        return cursorToCommune(cursor);
    }

    public List<Integer> getAllCP() {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {COL_CP}, null, null, null, null, null);
        return cursorToCPArray(cursor);
    }

    private List<Integer> cursorToCPArray(Cursor cursor) {
        if (cursor.getCount() == 0) return null;
        else {
            List<Integer> list = new ArrayList<Integer>();
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                int i = 0;
                int codePostal = cursor.getInt(NUM_COL_CP);
                list.add(codePostal);
                cursor.moveToNext();
            }
            return list;
        }
    }

    private Commune cursorToCommune(Cursor cursor) {
        if (cursor.getCount() == 0) return null;
        else {
            Commune commune = new Commune();
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                commune.setCodePostal(cursor.getInt(NUM_COL_CP));
                commune.setNom(cursor.getString(NUM_COL_NOM));
                commune.setRamassageBlanche(cursor.getInt(NUM_COL_RAM_BLANCHE));
                commune.setRamassageBlancheBis(cursor.getInt(NUM_COL_RAM_BLANCHE_BIS));
                commune.setRamassageBleue(cursor.getInt(NUM_COL_RAM_BLEUE));
                commune.setRamassageJaune(cursor.getInt(NUM_COL_RAM_JAUNE));
                commune.setRamassageVert(cursor.getInt(NUM_COL_RAM_VERTE));
                commune.setRamassageOrange(cursor.getInt(NUM_COL_RAM_ORANGE));
            }
            cursor.close();
            return commune;
        }
    }

    public long countRow() {
        long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_NAME);
        return count;
    }


}

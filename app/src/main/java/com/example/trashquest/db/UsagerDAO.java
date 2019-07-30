package com.example.trashquest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsagerDAO {

    private static final int VERSION_DB = 3;
    private static final String NOM_DB = "usager.db";

    private static final String TABLE_NAME = "USAGER";
    private static final String COL_LOGIN = "LOGIN";
    private static final int NUM_COL_LOGIN = 0;
    private static final String COL_CP = "USAGER_CODE_POSTAL";
    private static final int NUM_COL_CP = 1;

    private SQLiteDatabase sqLiteDatabase;

    private DbHelper dbHelper;

    public UsagerDAO(Context context) {
        dbHelper = new DbHelper(context, NOM_DB, null, VERSION_DB);
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

    public long insertUsager(Usager usager) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_LOGIN, usager.getLogin());
        contentValues.put(COL_CP, usager.getCodePostal());

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public Usager getUsagerWithLogin(String login) {
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {COL_LOGIN, COL_CP}, COL_LOGIN
                + " LIKE \"" + login + "\"", null, null, null, null);
        return cursorToUsager(cursor);
    }

    private Usager cursorToUsager(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        } else {
        cursor.moveToFirst();
        Usager usager = new Usager();
        usager.setLogin(cursor.getString(NUM_COL_LOGIN));
        usager.setCodePostal(cursor.getInt(NUM_COL_CP));
        cursor.close();
        return usager;}
    }

}

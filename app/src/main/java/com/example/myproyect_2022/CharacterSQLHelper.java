package com.example.myproyect_2022;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CharacterSQLHelper extends SQLiteOpenHelper {

    private String sqlQueryCreateTable = "CREATE TABLE Characters(id INTEGER, favorite TEXT)";

    private static int version = 1;
    private static String name = "Marvel";
    private static SQLiteDatabase.CursorFactory factory = null;

    public CharacterSQLHelper(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQueryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.android.medialecte.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.medialecte.provider.DialectContract.DialectEntry;
/**
 * Created by dmidma on 12/8/17.
 */

public class DialectDbHelper extends SQLiteOpenHelper {

    // declare database name and its version
    private static final String DATABASE_NAME = "dialect.db";
    private static final int DATABASE_VERSION = 1;


    public DialectDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_DIALECT_TABLE = "CREATE TABLE " + DialectEntry.TABLE_NAME + " (" +
                DialectEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DialectEntry.COLUMN_FRENCH_WORD + " TEXT NOT NULL, " +
                DialectEntry.COLUMN_ARABIC_WORD + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(SQL_CREATE_DIALECT_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // remove and recreate when new version
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DialectEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

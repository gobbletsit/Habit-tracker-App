package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gobov on 6/22/2017.
 */

public class MacroDbHelper extends SQLiteOpenHelper {

    /* specifying the database name and version */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "macros.db";


    /* class constructor with passed in name and version of the database */
    public MacroDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* creating a database with these exact data types */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + MacroContract.MacroEntry.TABLE_NAME + " ("
                + MacroContract.MacroEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MacroContract.MacroEntry.COLUMN_DAY + " TEXT DEFAULT 'Mon ' , "
                + MacroContract.MacroEntry.COLUMN_PROTEIN + " INTEGER NOT NULL DEFAULT 0, "
                + MacroContract.MacroEntry.COLUMN_FAT + " INTEGER NOT NULL DEFAULT 0, "
                + MacroContract.MacroEntry.COLUMN_CARB + " INTEGER NOT NULL DEFAULT 0, "
                + MacroContract.MacroEntry.COLUMN_TOTAL + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}

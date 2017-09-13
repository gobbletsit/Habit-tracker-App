package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.habittrackerapp.data.MacroContract.MacroEntry;
import com.example.android.habittrackerapp.data.MacroDbHelper;


public class MacroTrackerActivity extends AppCompatActivity {

    private final static String LOG_TAG = MacroTrackerActivity.class.getName();

    private MacroDbHelper dbHelper;

    /* user input variables */
    private String dayOfTheWeekStringEntry;
    private int proteinEntry;
    private int fatEntry;
    private int carbEntry;
    private int totalCalorieCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MacroDbHelper(this);
        insertData();
        readData();
    }

    /* to read data from the created database */
    private Cursor readData() {

        /* to get the readable from the database */
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /* an array of projection strings to pass in a cursor query */
        String[] projection = {
                MacroEntry._ID, MacroEntry.COLUMN_DAY, MacroEntry.COLUMN_PROTEIN, MacroEntry.COLUMN_FAT, MacroEntry.COLUMN_CARB, MacroEntry.COLUMN_TOTAL
        };

        /* returning the query in a cursor object */
        Cursor cursor = db.query(MacroEntry.TABLE_NAME, projection, null, null, null, null, null);

        try {

            /* getting the column indexes from cursor object to use for getting the results */
            int idColumnInex = cursor.getColumnIndex(MacroEntry._ID);
            int dayColumnIndex = cursor.getColumnIndex(MacroEntry.COLUMN_DAY);
            int proteinColumnIndex = cursor.getColumnIndex(MacroEntry.COLUMN_PROTEIN);
            int fatColumnIndex = cursor.getColumnIndex(MacroEntry.COLUMN_FAT);
            int carbColumnIndex = cursor.getColumnIndex(MacroEntry.COLUMN_CARB);
            int totalColumnIndex = cursor.getColumnIndex(MacroEntry.COLUMN_TOTAL);

            while (cursor.moveToNext()){

                /* getting the results from cursor object using column indexes */
                int currentId = cursor.getInt(idColumnInex);
                String currentDay = cursor.getString(dayColumnIndex);
                int currentProtein = cursor.getInt(proteinColumnIndex);
                int currentFat = cursor.getInt(fatColumnIndex);
                int currentCarb = cursor.getInt(carbColumnIndex);
                int currentTotal = cursor.getInt(totalColumnIndex);

                /* logging the results */
                Log.e(LOG_TAG, currentId + " " + currentDay + " " + currentProtein + " " + currentFat + " " + currentCarb
                + " " + currentTotal);

            }
        } finally {
            /* closing the cursor object upon finishing */
            cursor.close();
        }
        return cursor;
    }

    /* to insert new data in a database on user inputs */
    private void insertData() {

        /* to be able to insert data in a database */
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        /* inserting the values using content values */
        values.put(MacroEntry.COLUMN_DAY, dayOfTheWeekStringEntry);
        values.put(MacroEntry.COLUMN_PROTEIN, proteinEntry);
        values.put(MacroEntry.COLUMN_FAT, fatEntry);
        values.put(MacroEntry.COLUMN_CARB, carbEntry);

        /* its multiplied by these numbers since they represent a calorie number of each macro (ex. 1 protein = 4 calories) */
        values.put(MacroEntry.COLUMN_TOTAL, ((proteinEntry * 4) + (fatEntry * 9) + (carbEntry * 4)));

        long newRowId = db.insert(MacroEntry.TABLE_NAME, null, values);

        if (newRowId == -1){
            Log.e(LOG_TAG, "Error with inserting row: " + newRowId);
        } else {
            Log.e(LOG_TAG, "Inserted with row id: " + newRowId);
        }
    }
}

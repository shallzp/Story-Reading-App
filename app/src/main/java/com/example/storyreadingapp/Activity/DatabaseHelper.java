package com.example.storyreadingapp.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userDatabase";
    private static final String TABLE_NAME = "users";
    private static final String COL_NAME = "name";
    private static final String COL_AGE = "age";
    private static final String COL_PHONE = "phoneNo";
    private static final String COL_GENDER = "gender";
    private static final String COL_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_PHONE + " TEXT PRIMARY KEY, " +
                COL_NAME + " TEXT, " +
                COL_AGE + " INTEGER, " +
                COL_GENDER + " TEXT, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addUser(String name, int age, String phoneNo, String gender, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_AGE, age);
        contentValues.put(COL_PHONE, phoneNo);
        contentValues.put(COL_GENDER, gender);
        contentValues.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.d("DatabaseHelper", "Insert Result: " + result); // Log the result
        return result != -1;
    }


    public boolean checkUserExists(String phoneNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_PHONE},
                COL_PHONE + "=?", new String[]{phoneNo},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        Log.d("DatabaseHelper", "User exists: " + exists + " for phoneNo: " + phoneNo);
        cursor.close();
        return exists;
    }

    public boolean validateUser(String phoneNo, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COL_PHONE},
                COL_PHONE + "=? AND " + COL_PASSWORD + "=?",
                new String[]{phoneNo, password},
                null, null, null);
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        Log.d("DatabaseHelper", "User validated: " + isValid + " for phoneNo: " + phoneNo);
        return isValid;
    }

}

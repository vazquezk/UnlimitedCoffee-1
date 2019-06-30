package com.unlimitedcoffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.mindrot.jbcrypt.BCrypt;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final byte[] databaseBytes = Hide.getDatabaseName();
    public static final String databaseName = new String(databaseBytes);
    //Database name and columns
    public static final String DATABASE_NAME = databaseName;
    public static final String TABLE_NAME = "user";
    public static final int DATABASE_VERSION = 1;
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_PHONE = "user_phone";
    public static final String COL_USER_PASSWORD = "user_password";

    //Creating Database
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_USER_PHONE + " STRING,"
            + COL_USER_PASSWORD + " TEXT)";
    //Drop SQL Table
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Class constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);

    }

    //Method to add new users
    public void addUser(String phoneNumber, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_USER_PHONE, phoneNumber);
        contentValues.put(COL_USER_PASSWORD, password);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    //Method to delete existing accounts
    public void deleteUser(String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by phone number
        db.delete(TABLE_NAME, COL_USER_PHONE + " = ?",
                new String[]{String.valueOf(phoneNumber)});
        db.close();
    }

    //Checks database for phone number and password
    public boolean checkUser(String phoneNumber, String password) {
        boolean isValid;
        String hashed_pword;

        // 1. pull saved password hash from db
        String[] columns = {COL_USER_PASSWORD};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_USER_PHONE + "=?";
        String[] selectionArgs = {phoneNumber};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            hashed_pword = cursor.getString(0);
            // 2. use jBcrypt checkpw() function to verify password
            if (BCrypt.checkpw(password, hashed_pword)) {
                isValid = true; // pword matches
            } else {
                isValid = false; // pword does not match
            }
        } else {
            isValid = false;
        }
        cursor.close();
        db.close();
        return isValid;
    }

    //Checks if phone number is already in database
    public boolean checkPhone(String phoneNumber) {

        String[] columns = {COL_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_USER_PHONE + "=?";
        String[] selectionArgs = {phoneNumber};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();

        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }
}

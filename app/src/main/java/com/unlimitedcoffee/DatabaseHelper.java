package com.unlimitedcoffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    //Database name and columns
    public static final String DATABASE_NAME = "UserManagement.db";
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

    public void addUser(String phoneNumber, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_USER_PHONE,phoneNumber);
        contentValues.put(COL_USER_PASSWORD, password);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public boolean checkUser(String phoneNumber, String password){

        String[] columns = {COL_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COL_USER_PHONE + "=?" + " and " + COL_USER_PASSWORD + "=?";

        String[] selectionArgs = {phoneNumber, password};

        Cursor cursor = db.query(TABLE_NAME, columns,selection,selectionArgs, null, null, null);

        int count = cursor.getCount();

        db.close();

        if(count > 0)
            return true;
        else
            return false;


    }

}

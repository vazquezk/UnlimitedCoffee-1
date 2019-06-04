package com.unlimitedcoffee;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionPreferences {

    //Shared Preferences
    SharedPreferences preferences;

    //Editor for Shared preferences
    Editor editor;

    //Context
    Context _context;

    //Shared preference file name
    private static final String PREF_NAME = "CoffeeTalkPref";

    //All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    //User name (make variable public to access from outside)
    public static final String KEY_NAME = "phoneNumber";


    //Constructor
    public SessionPreferences(Context context) {
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void createLoginSession(String phoneNumber) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, phoneNumber);

        // commit changes
        editor.commit();
    }

    public void checkLogin() {
        //Check login status
        if (!this.isLoggedIn()) {
            //user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            //Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Staring Login Activity
            _context.startActivity(i);
        }

    }

    public void logoutUser() {
        //Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        //After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        //Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Staring Login Activity
        _context.startActivity(i);
    }

    public String getKeyName(){ return preferences.getString(KEY_NAME, "Not found");
    }

    // Get Login State
    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }
}
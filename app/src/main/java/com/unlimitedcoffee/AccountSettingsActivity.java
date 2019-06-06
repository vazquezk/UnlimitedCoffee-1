package com.unlimitedcoffee;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountSettingsActivity extends AppCompatActivity {

    Button mDeleteAccButton;
    TextView mTextViewLoggedInAs;
    SessionPreferences session;
    DatabaseHelper db;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instance of builder for alert dialog
        builder = new AlertDialog.Builder(this);

        //Creating instance of Session preferences to store/check user login status
        session = new SessionPreferences(getApplicationContext());
        session.checkLogin();

        //Instance of db helper
        db = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        mDeleteAccButton = (Button) findViewById(R.id.delete_button);
        mTextViewLoggedInAs = (TextView) findViewById(R.id.logged_in_as);
        mTextViewLoggedInAs.setText("Currently logged in as: " + session.getKeyName());

        //OnClickListener for delete account button
        mDeleteAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog to confirm user wants to delete account
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountSettingsActivity.this);
                builder.setMessage(R.string.dialog_message);
                builder.setCancelable(true);
                //If yes, delete account and logout
                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deleteUser(session.getKeyName());
                                session.logoutUser();
                            }
                        });
                //If no, cancel and back to account settings
                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
    }
}

package com.unlimitedcoffee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SessionPreferences session;

    EditText mTextPhoneNumber;
    EditText mTextPassword;
    EditText mTextCfPassword;
    Button mRegisterButton;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        session = new SessionPreferences(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        mTextPhoneNumber = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextCfPassword = (EditText) findViewById(R.id.edittext_cf_password);
        mRegisterButton = (Button) findViewById(R.id.register_button);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = mTextPhoneNumber.getText().toString().trim();
                String password = mTextPassword.getText().toString().trim();
                String cf_password = mTextCfPassword.getText().toString().trim();

                if(password.equals(cf_password)){
                    session.createLoginSession(phoneNumber);
                    db.addUser(phoneNumber,password);
                    Intent smsApp = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(smsApp);
                }else {
                    Toast.makeText(RegisterActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

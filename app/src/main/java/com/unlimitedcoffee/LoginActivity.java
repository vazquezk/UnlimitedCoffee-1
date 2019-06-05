package com.unlimitedcoffee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.i18n.phonenumbers.PhoneNumberUtil;


public class LoginActivity extends AppCompatActivity {

    SessionPreferences session;

    EditText mTextPhoneNumber;
    EditText mTextPassword;
    Button mLoginButton;
    TextView mTextViewRegister;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        session = new SessionPreferences(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        mTextPhoneNumber = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mTextViewRegister = (TextView) findViewById(R.id.textview_register);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = mTextPhoneNumber.getText().toString().trim();
                String password = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(phoneNumber,password);

                if(res == true) {
                    session.createLoginSession(phoneNumber);
                    Intent smsApp = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(smsApp);

                }else{
                    Toast.makeText(LoginActivity.this, "Wrong password/phone number",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

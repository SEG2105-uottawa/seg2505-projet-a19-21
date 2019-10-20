package com.a19_21.clinicapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a19_21.clinicapp.R;

public class SignInActivity extends AppCompatActivity {

    private TextView titlePage;
    private EditText usernameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        titlePage = (TextView) findViewById(R.id.activity_sign_title_txt);
        usernameInput = (EditText) findViewById(R.id.activity_sign_username_input);
        emailInput = (EditText) findViewById(R.id.activity_sign_email_input);
        passwordInput = (EditText) findViewById(R.id.activity_sign_password_input);
        createBtn = (Button) findViewById(R.id.activity_sign_create_btn);

    }
}

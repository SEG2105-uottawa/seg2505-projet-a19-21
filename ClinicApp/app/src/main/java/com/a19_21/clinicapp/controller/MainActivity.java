package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView titleAppTxt;
    private EditText emailInput;
    private EditText passwordInput;
    private Button loginBtn;
    private TextView createAccountTxt;
    private Button signInBtn;

    // Firebase Authentication
    private FirebaseAuth mAuth ;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Log in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleAppTxt = (TextView) findViewById(R.id.activity_main_title_txt);
        emailInput = (EditText) findViewById(R.id.activity_main_email_input);
        passwordInput = (EditText) findViewById(R.id.activity_main_password_input);
        loginBtn = (Button) findViewById(R.id.activity_main_login_btn);
        createAccountTxt = (TextView) findViewById(R.id.activity_main_create_txt);
        signInBtn = (Button) findViewById(R.id.activity_main_sign_btn);

        loginBtn.setEnabled(false);

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0) {
                    loginBtn.setEnabled(false);
                } else {
                    loginBtn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()==0) {
                    loginBtn.setEnabled(false);
                } else {
                    loginBtn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mAuth = FirebaseAuth.getInstance();

        // A IMPLEMENTER SI ON A LE TEMPS
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //redirect
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        // SENDS THE USER TO SIGN UP
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignIn = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(goToSignIn);
            }
        });
    }

    private void login() {

        final String email = emailInput.getText().toString().trim();
        final String password = passwordInput.getText().toString().trim();

        // SIGN IN EXISTING USERS //
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmailAndPassword:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            isEmailValid(email);
                            isPasswordValid(password);
                            Intent goToWelcome = new Intent(MainActivity.this, WelcomeActivity.class);
                            startActivity(goToWelcome);

                        } else {
                            System.out.println("Enter Task NOT Successful");
                            Log.w(TAG, "signInWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //email invalide
    private final boolean isEmailValid(CharSequence email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //mot de passe trop court
    private final boolean isPasswordValid(CharSequence password){
        Matcher matcher;
        final String PASSWORD_PATTERN = "^.{5,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}

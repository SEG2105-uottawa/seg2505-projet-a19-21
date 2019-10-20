package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a19_21.clinicapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

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

        mAuth = FirebaseAuth.getInstance();

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

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        // SIGN IN EXISTING USERS //
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()

        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                Log.d(TAG, " Vérification : signIn With Email : " + task.isSuccessful());
                //  If sign in succeeds i.e if task.isSuccessful(); returns true then the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                Intent intent = new Intent(MainActivity.this , WelcomeActivity.class);
                startActivity(intent);

                // If sign in fails, display a message to the user.
                if (!task.isSuccessful()) {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        Log.e(TAG, e.getMessage());
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        Log.e(TAG, e.getMessage());
                    } catch (FirebaseNetworkException e) {
                        Log.e(TAG, e.getMessage());
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }

                }
            }

        });

    }


    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    */



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
}

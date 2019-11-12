package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView titlePage;
    private Spinner typeAccount;
    private EditText usernameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText passwordInput2;
    private Button createBtn;

    // Firebase Authentication
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private static final String TAG = "Sign up";

    // Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        titlePage = (TextView) findViewById(R.id.activity_sign_title_txt);
        usernameInput = (EditText) findViewById(R.id.activity_sign_username_input);
        emailInput = (EditText) findViewById(R.id.activity_sign_email_input);
        passwordInput = (EditText) findViewById(R.id.activity_sign_password_input);
        passwordInput2 = (EditText) findViewById(R.id.activity_sign_password_input2);
        createBtn = (Button) findViewById(R.id.activity_sign_create_btn);

        // OBJECT FOR CHOOSING THE ACCOUNT TYPE
        typeAccount = findViewById(R.id.activity_sign_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(SignInActivity.this, R.array.typeAccountsArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAccount.setAdapter(adapter);

        typeAccount.setOnItemSelectedListener(SignInActivity.this);

        databaseUsers = database.getReference("user");

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });



    }

    // SHOW A TOAST MESSAGE TO CONFIRM THE CHOICE
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    private void signUp() {

        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();
        final String confirmPassword = passwordInput2.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            addNewUser();
                            isEmailValid(email);
                            isPasswordValid(password);
                            Intent goToWelcome = new Intent(SignInActivity.this, WelcomeActivity.class);
                            startActivity(goToWelcome);

                        } else {
                            System.out.println("Enter Task NOT Successful");
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private boolean addNewUser(){
        System.out.println("Enter addNewUser");
        String username = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String confirmPassword = passwordInput.getText().toString();
        String userType = typeAccount.getSelectedItem().toString();

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword) && password == confirmPassword) {
            // Creates different account types
            User user = new User(id, username, email, confirmPassword, userType);
            databaseUsers.child(id).setValue(user);
            return true;

        } else {
            Toast.makeText(SignInActivity.this, "Information missing or passwords do not match.",
                    Toast.LENGTH_LONG).show();
            return false;
        }

    }
}

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
import com.a19_21.clinicapp.model.Employee;
import com.a19_21.clinicapp.model.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView titlePage;
    private Spinner typeAccount;
    private EditText usernameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button createBtn;

    // Firebase Authentication
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private FirebaseAuth.AuthStateListener mAuthListener;
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


    private void signUp() {

        System.out.println("Enter Sign up");
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Enter Task Successful");
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            addNewUser();
                            Intent goToWelcome = new Intent(SignInActivity.this, WelcomeActivity.class);
                            startActivity(goToWelcome);
                        } else {
                            System.out.println("Enter Task NOT Successful");
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }

    private boolean addNewUser(){
        System.out.println("Enter addNewUser");
        String username = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String userType = typeAccount.getSelectedItem().toString();

        // Generates an id for the user
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //String id = databaseUsers.push().getKey();


        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            // Creates different accounts depending on the type
            if (userType.equals("Employee")){
                System.out.println("Enter Employee");
                Employee employee = new Employee(id,username, email, password);
                databaseUsers.child(id).setValue(employee);

                return true;

            } else if (userType.equals("Patient")) {
                Patient patient = new Patient(id,username, email, password);

                databaseUsers.child(id).setValue(patient);
                return true;

            } else {
                return false;
            }

        } else {
            Toast.makeText(SignInActivity.this, "Information missing",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}

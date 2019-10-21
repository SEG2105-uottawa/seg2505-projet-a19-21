package com.a19_21.clinicapp.controller;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Employee;
import com.a19_21.clinicapp.model.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ;


    private TextView greetingTxt;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        databaseUsers = database.getReference("user");


        greetingTxt = (TextView) findViewById(R.id.activity_welcome_greeting_txt);
        greetingTxt.setText("Welcome !");
        //displayWelcome();


    }

    /*
    private void displayWelcome() {

        databaseUsers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                User user = dataSnapshot.getValue(User.class);
                String role;

                greetingTxt.setText(user);

            }

            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){
                System.out.println("Read failed :" + databaseError.getCode());
            }

        });
    }
     */
}
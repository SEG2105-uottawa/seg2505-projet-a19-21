package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    private TextView greetingTxt;
    private Button createServiceBtn;
    private Button manageServicesBtn;
    private Button manageUsersBtn;

    // FIREBASE AUTHENTICATION
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    // FIREBASE DATABASE
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        greetingTxt = (TextView) findViewById(R.id.activity_admin_greeting_txt);
        createServiceBtn = (Button) findViewById(R.id.activity_admin_createservice_btn);
        manageServicesBtn = (Button) findViewById(R.id.activity_admin_manageservices_btn);
        manageUsersBtn = (Button) findViewById(R.id.activity_admin_manageusers_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user/"+firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                greetingTxt.setText("Welcome "+user.getUsername()+", you're connected as "+user.getType());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        createServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCreateService = new Intent(AdminActivity.this, CreateServiceActivity.class);
                startActivity(goToCreateService);
            }
        });

        manageServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToManageServices = new Intent(AdminActivity.this, ManageServicesActivity.class);
                startActivity(goToManageServices);
            }
        });

        manageUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToManageUsers = new Intent(AdminActivity.this, ManageUsersActivity.class);
                startActivity(goToManageUsers);
            }
        });
    }
}

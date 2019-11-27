package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


public class SearchActivity extends AppCompatActivity {


    private Button clinicsearchbtn;
    private Button servicesearchbtn ;
    private Button timesearchbtn;
    private TextView greetTxt;

    private DatabaseReference patientRef;


    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:{
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        clinicsearchbtn = (Button) findViewById(R.id.clinicsearchbtn);
        servicesearchbtn= (Button) findViewById(R.id.servicesearchbtn);
        timesearchbtn= (Button) findViewById(R.id.timesearchbtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        patientRef = FirebaseDatabase.getInstance().getReference("user/" + firebaseUser.getUid());
        greetTxt = (TextView) findViewById(R.id.search_activity_greeting_txt);

        // SET THE GREETING TEXT FOR PATIENT
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                greetTxt.setText("Welcome " + user.getUsername() + ", you're connected as " + user.getType());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        clinicsearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clinicSearch = new Intent(SearchActivity.this, ClinicSearchActivity.class);
                startActivity(clinicSearch);
            }
        });

        servicesearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceSearch = new Intent(SearchActivity.this,SearchServiceActivity.class);
                startActivity(serviceSearch);
            }
        });

        timesearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timeSearch = new Intent(SearchActivity.this, TimeSearchActivity.class);
                startActivity(timeSearch);
            }
        });
    }
}
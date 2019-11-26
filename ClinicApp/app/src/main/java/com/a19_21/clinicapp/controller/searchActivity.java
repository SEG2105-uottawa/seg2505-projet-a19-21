package com.a19_21.clinicapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a19_21.clinicapp.R;


public class searchActivity extends AppCompatActivity {


    private Button clinicsearchbtn;
    private Button servicesearchbtn ;
    private Button timesearchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        clinicsearchbtn = (Button) findViewById(R.id.timesearchbtn);
        servicesearchbtn= (Button) findViewById(R.id.timesearchbtn);
        timesearchbtn= (Button) findViewById(R.id.timesearchbtn);

        clinicsearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clinicSearch = new Intent(searchActivity.this,clinicSeachActivity.class);
                startActivity(clinicSearch);
            }
        });

        servicesearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceSearch = new Intent(searchActivity.this,serviceSeachActivity.class);
                startActivity(serviceSearch);
            }
        });

        timesearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timeSearch = new Intent(searchActivity.this,timeSeachActivity.class);
                startActivity(timeSearch);
            }
        });
    }
}
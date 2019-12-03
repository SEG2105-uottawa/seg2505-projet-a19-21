package com.a19_21.clinicapp.controller;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Clinic;
import com.a19_21.clinicapp.model.ClinicHours;
import com.a19_21.clinicapp.model.ClinicsList;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

class TimeSearchActivity extends AppCompatActivity {

    private DatabaseReference databaseHours;
    private ListView openClinicListView;

    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;


    private List<Clinic> openClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_search);
        monday = (CheckBox) findViewById(R.id.monday);
        tuesday = (CheckBox) findViewById(R.id.tuesday);
        wednesday = (CheckBox) findViewById(R.id.wednesday);
        thursday = (CheckBox) findViewById(R.id.thursday);
        friday = (CheckBox) findViewById(R.id.friday);
        saturday = (CheckBox) findViewById(R.id.saturday);
        sunday = (CheckBox) findViewById(R.id.sunday);

        Clinic clinic;

        if (monday.isChecked()){
            /*

            for(){
                if(clinic.getHours().isOpen(0)){
                    openClinic.add(clinic);
                }
            }
           */
            //display clinics open on monday
        }
        if (tuesday.isChecked()){
            //display clinics open on tuesday
        }
        if (wednesday.isChecked()){
            //display clinics open on wednesday
        }
        if (thursday.isChecked()){
            //display clinics open on thursday
        }
        if (friday.isChecked()){
            //display clinics open on friday
        }
        if (saturday.isChecked()){
            //display clinics open on saturday
        }
        if (sunday.isChecked()){
            //display clinics open on sunday
        }


        }
    }




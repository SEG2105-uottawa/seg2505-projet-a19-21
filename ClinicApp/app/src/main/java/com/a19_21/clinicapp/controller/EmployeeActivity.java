package com.a19_21.clinicapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a19_21.clinicapp.R;

public class EmployeeActivity extends AppCompatActivity {

    private TextView clinicName;
    private TextView adress1;
    private TextView adress2;
    private TextView phoneNumber;
    private TextView todaysHours;
    private Button createClinicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        clinicName = (TextView) findViewById(R.id.activity_employee_clinic_name_txt);
        adress1 = (TextView) findViewById(R.id.activity_employee_adress_txt);
        adress2 = (TextView) findViewById(R.id.activity_employee_city_txt);
        phoneNumber = (TextView) findViewById(R.id.activity_employee_phone_txt);
        todaysHours = (TextView) findViewById(R.id.activity_employee_hoursOp_txt);

        createClinicBtn = findViewById(R.id.activity_employee_create_clinic_btn);

        createClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateClinic = new Intent(EmployeeActivity.this, CreateClinicActivity.class);
                startActivity(goToCreateClinic);
            }
        });
    }
}

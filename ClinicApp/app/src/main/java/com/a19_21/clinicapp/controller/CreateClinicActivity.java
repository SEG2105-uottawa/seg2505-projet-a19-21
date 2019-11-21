package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Clinic;
import com.a19_21.clinicapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateClinicActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText newClinicName;
    private EditText clinicPhone;

    private Button createBtn;

    // Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseUsers;
    DatabaseReference databaseClinics;
    DatabaseReference databaseAdresses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_clinic);

        newClinicName = (EditText) findViewById(R.id.activity_create_clinic_name_input);
        clinicPhone = (EditText) findViewById(R.id.activity_create_clinic_phone_input);
        createBtn = (Button) findViewById(R.id.activity_create_clinic_create_btn);

        databaseUsers = database.getReference("user");
        databaseClinics = database.getReference("clinic");
        databaseAdresses = database.getReference("adress");

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClinic();
                finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void createClinic(){

        // Get user ID to associate him to clinic
        String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // ESSENTIEL POUR LE CONSTRUCTEUR
        String name = newClinicName.getText().toString().trim();

        // PAS ESSENTIEL
        String phone = clinicPhone.getText().toString().trim();

        if(!TextUtils.isEmpty(name)) {

            String id = databaseClinics.push().getKey();

            Clinic clinic = new Clinic(id, name, UserId);

            if (!TextUtils.isEmpty(phone)) {
                clinic.setPhoneNumber(phone);
            }

            databaseClinics.child(id).setValue(clinic);

            Toast.makeText(this, "Clinic created ^^ !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }

}

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
import com.a19_21.clinicapp.model.Adress;
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
    private EditText clinicAdress;
    private EditText clinicCity;
    private EditText clinicProvince;
    private Spinner clinicCountry;
    private EditText clinicZipcode;
    private Button createBtn;

    // Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseUsers;
    DatabaseReference databaseClinics;
    DatabaseReference databaseAdresses;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_clinic);

        newClinicName = (EditText) findViewById(R.id.activity_create_clinic_name_input);
        clinicPhone = (EditText) findViewById(R.id.activity_create_clinic_phone_input);
        clinicAdress = (EditText) findViewById(R.id.activity_create_clinic_adress_input);
        clinicCity = (EditText) findViewById(R.id.activity_create_clinic_city_input);
        clinicProvince = (EditText) findViewById(R.id.activity_create_clinic_province_input);
        clinicZipcode = (EditText) findViewById(R.id.activity_create_clinic_zipcode_input);
        createBtn = (Button) findViewById(R.id.activity_create_clinic_create_btn);

        databaseUsers = database.getReference("user");
        databaseClinics = database.getReference("clinic");
        databaseAdresses = database.getReference("adress");

        clinicCountry = findViewById(R.id.activity_create_clinic_country_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CreateClinicActivity.this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clinicCountry.setAdapter(adapter);

        clinicCountry.setOnItemSelectedListener(CreateClinicActivity.this);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClinic();
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
        String adress = clinicAdress.getText().toString().trim();
        String city = clinicCity.getText().toString().trim();
        String province = clinicProvince.getText().toString().trim();
        String zipcode = clinicZipcode.getText().toString().trim();
        String country = clinicCountry.getSelectedItem().toString().trim();

        if(!TextUtils.isEmpty(name)) {

            String id = databaseClinics.push().getKey();

            Clinic clinic = new Clinic(id, name, UserId);

            if (!TextUtils.isEmpty(phone)) {
                clinic.setPhoneNumber(phone);
            }

            createClinAdress(clinic, adress, city, province, zipcode, country);
            associateClinicToEmployee(id);

            databaseClinics.child(id).setValue(clinic);



            Toast.makeText(this, "Clinic created ^^ !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }

    public void createClinAdress(Clinic clinic, String adress, String city, String province, String zipcode, String country) {

        if (!TextUtils.isEmpty(adress) && !TextUtils.isEmpty(city)) {

            String adressId = databaseAdresses.push().getKey();

            Adress clinAdress = new Adress(adressId, adress, city);

            if (!TextUtils.isEmpty(province)) {
                clinAdress.setProvince(province);
            }
            if (!TextUtils.isEmpty(zipcode)) {
                clinAdress.setPostalCode(zipcode);
            }
            if (!TextUtils.isEmpty(country)) {
                clinAdress.setCountry(country);
            }
            databaseAdresses.child(adressId).setValue(clinAdress);

            clinic.setAdressID(adressId);
        }
    }


    public void associateClinicToEmployee(String clinicID) {

        final String userClinID = clinicID;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user/"+firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                user.setClinicID(userClinID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

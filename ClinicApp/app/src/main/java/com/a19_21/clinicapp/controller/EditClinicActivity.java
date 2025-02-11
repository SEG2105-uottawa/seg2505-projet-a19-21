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
import android.widget.EditText;



import com.a19_21.clinicapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EditClinicActivity extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private Button updateBtn;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference clinicRef;
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
        setContentView(R.layout.activity_edit_clinic);

        name = findViewById(R.id.activity_edit_clinic_name_input);
        phone = findViewById(R.id.activity_edit_clinic_phone_input);
        updateBtn = findViewById(R.id.activity_update_clinic_btn);

        System.out.println("DEBUG NAME : " + name);

        name.setText(getIntent().getStringExtra("cName"));
        phone.setText(getIntent().getStringExtra("cPhone"));

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateClinic();
                finish();
            }
        });

    }

    public void updateClinic() {

        String newName = name.getText().toString().trim();
        String newPhone = phone.getText().toString().trim();

        String clinicID = getIntent().getStringExtra("clID");

        clinicRef = database.getReference("clinic").child(clinicID);
        System.out.println("REF : " + clinicRef);

        clinicRef.child("clinicName").setValue(newName);
        clinicRef.child("phoneNumber").setValue(newPhone);

    }
}

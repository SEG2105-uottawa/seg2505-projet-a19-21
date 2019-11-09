package com.a19_21.clinicapp.controller;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.a19_21.clinicapp.R;

import com.a19_21.clinicapp.model.Service;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateServiceActivity extends AppCompatActivity {

    private EditText serviceNameInput;
    private EditText serviceDescripInput;
    private Button createService;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        serviceNameInput = (EditText) findViewById(R.id.activity_create_service_name_input);
        serviceDescripInput = (EditText) findViewById(R.id.activity_create_service_description_input);
        createService = (Button) findViewById(R.id.activity_create_service_create_btn);


        databaseServices = database.getReference("service");


        //Create new service
        createService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewService();
            }
        });
    }



    public void createNewService() {

        String name = serviceNameInput.getText().toString().trim();
        String description = serviceDescripInput.getText().toString().trim();


        if( !TextUtils.isEmpty(name) && !TextUtils.isEmpty(description)) {
            String id = databaseServices.push().getKey();
            Service service = new Service(name, description);
            databaseServices.child((id)).setValue(service);


        }
        else{
            Toast.makeText(this,"Field missing !",Toast.LENGTH_SHORT).show();
        }
    }
}



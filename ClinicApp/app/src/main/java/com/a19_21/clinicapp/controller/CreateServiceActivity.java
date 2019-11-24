package com.a19_21.clinicapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.a19_21.clinicapp.R;

import com.a19_21.clinicapp.model.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateServiceActivity extends AppCompatActivity {

    private EditText serviceNameInput;
    private EditText serviceDescripInput;
    private Button createService;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseServices;
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

            Service service = new Service(id, name, description);
            databaseServices.child((id)).setValue(service);

            Toast.makeText(this,"Service created",Toast.LENGTH_SHORT).show();

            serviceNameInput.setText("");
            serviceDescripInput.setText("");

        }
        else{
            Toast.makeText(this,"Field missing !",Toast.LENGTH_SHORT).show();
        }

    }
}



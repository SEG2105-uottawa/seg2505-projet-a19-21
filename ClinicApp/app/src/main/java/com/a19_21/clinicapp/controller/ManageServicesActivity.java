package com.a19_21.clinicapp.controller;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Service;
import com.a19_21.clinicapp.model.ServicesList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageServicesActivity extends AppCompatActivity {

    private DatabaseReference databaseServices;
    private ListView servicesListView;
    private List<Service> servicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        databaseServices = FirebaseDatabase.getInstance().getReference("service");

        servicesListView = (ListView) findViewById(R.id.manage_services_serviceslistview);
        servicesList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                servicesList.clear();

                for (DataSnapshot servicesSnapshot : dataSnapshot.getChildren()) {
                    Service service = servicesSnapshot.getValue(Service.class);
                    servicesList.add(service);
                }

                ServicesList adapter = new ServicesList(ManageServicesActivity.this, servicesList);
                servicesListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
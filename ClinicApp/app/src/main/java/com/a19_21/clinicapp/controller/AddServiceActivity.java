package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Service;
import com.a19_21.clinicapp.model.ServiceListClinic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddServiceActivity extends AppCompatActivity {

    DatabaseReference databaseServices;
    private ListView servicesListClView;
    private List<Service> servicesListCl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        databaseServices = FirebaseDatabase.getInstance().getReference("service");

        servicesListClView = (ListView) findViewById(R.id.list_service_cl_listview);
        servicesListCl = new ArrayList<>();

        servicesListClView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Service service = servicesListCl.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(AddServiceActivity.this);
                LayoutInflater inflater = AddServiceActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.add_service_box, null);

                builder.setView(dialogView);

                final EditText serviceDesc = dialogView.findViewById(R.id.add_service_box_description_input);
                final EditText servicePrice = dialogView.findViewById(R.id.add_service_box_price_input);

                final Button addBtn = dialogView.findViewById(R.id.add_service_box_btn);

                builder.setTitle(service.getName())
                        .create()
                        .show();

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String description = serviceDesc.getText().toString().trim();
                        String price = servicePrice.getText().toString().trim();

                        // EMPECHER L'APPLI DE CRASH QUAND ON NE MET RIEN OU QU'ON rentre des lettres

                        if (!description.equals(null) && !price.equals(null)) {
                            double fee = Double.parseDouble(price);

                            String clID = getIntent().getStringExtra("clID");
                            System.out.println("CHECK clID : " + clID);
                            DatabaseReference refClinic = FirebaseDatabase.getInstance().getReference("clinic").child(clID).child("services_available");
                            System.out.println("CHECK REF CLINIC : " + refClinic);

                            String servID = refClinic.push().getKey();

                            Service serviceCl = new Service(servID, service.getName(), description, fee);

                            refClinic.child(servID).setValue(serviceCl);

                            Toast.makeText(AddServiceActivity.this, "Service added", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(AddServiceActivity.this, "Please fill all the info", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                servicesListCl.clear();

                for (DataSnapshot servicesSnapshot : dataSnapshot.getChildren()) {
                    Service service = servicesSnapshot.getValue(Service.class);
                    servicesListCl.add(service);
                }

                ServiceListClinic adapter = new ServiceListClinic(AddServiceActivity.this, servicesListCl);
                servicesListClView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

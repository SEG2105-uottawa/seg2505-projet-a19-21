package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Service;
import com.a19_21.clinicapp.model.ServiceListEmployee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManageServicesActivity extends AppCompatActivity {

    DatabaseReference databaseServices;

    private ListView serviceListEmployee;
    private List<Service> serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_manage_services);

        serviceList = new ArrayList<>();
        serviceListEmployee = findViewById(R.id.manage_services_employee_listview);

        String clinicID = getIntent().getStringExtra("clID");

        databaseServices = FirebaseDatabase.getInstance().getReference("clinic").child(clinicID).child("services_available");

        serviceListEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final Service service = serviceList.get(position);

                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(EmployeeManageServicesActivity.this);

                LayoutInflater inflater = EmployeeManageServicesActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.update_del_service_box, null);
                builder.setView(dialogView);

                final EditText serviceDescription = dialogView.findViewById(R.id.manage_service_box_description_input);
                final EditText servicePrice = dialogView.findViewById(R.id.manage_service_box_price_input);
                final Button updateBtn = dialogView.findViewById(R.id.update_service_box_btn);
                final Button deleteBtn = dialogView.findViewById(R.id.delete_service_box_btn);

                serviceDescription.setText(service.getDescription());
                servicePrice.setText(Double.toString(service.getFee()));


                builder.setTitle(service.getName())
                        .create()
                        .show();

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = service.getName();
                        String desc = serviceDescription.getText().toString().trim();
                        double fee = Double.parseDouble(servicePrice.getText().toString().trim());
                        String id = service.getServiceId();

                        Service newService = new Service(id, name, desc, fee);

                        DatabaseReference ref = databaseServices.child(id);

                        ref.setValue(newService);

                    }
                });

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = service.getServiceId();

                        DatabaseReference ref = databaseServices.child(id);

                        ref.removeValue();
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

                serviceList.clear();

                for (DataSnapshot serviceSnapshot : dataSnapshot.getChildren()) {
                    Service service = serviceSnapshot.getValue(Service.class);

                    serviceList.add(service);
                }

                ServiceListEmployee adapter = new ServiceListEmployee(EmployeeManageServicesActivity.this, serviceList);
                serviceListEmployee.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

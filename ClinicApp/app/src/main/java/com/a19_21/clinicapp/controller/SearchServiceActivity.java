package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Clinic;
import com.a19_21.clinicapp.model.Service;
import com.a19_21.clinicapp.model.ServiceSearchList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceActivity extends AppCompatActivity {

    private ListView serviceSearchListView;
    private List<Service> serviceSearchList;
    private DatabaseReference databaseClinicsServices;
    private ServiceSearchList adapter;

    private SearchView search ;
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
        setContentView(R.layout.activity_search_service);

        databaseClinicsServices = FirebaseDatabase.getInstance().getReference("clinic");

        serviceSearchList = new ArrayList<>();
        serviceSearchListView = (ListView) findViewById(R.id.activity_service_search_listView);

        search = findViewById(R.id.activity_service_search_searchView);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });


        serviceSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = serviceSearchList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchServiceActivity.this);

                LayoutInflater inflater = SearchServiceActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.see_service_box, null);
                builder.setView(dialogView);

                TextView serviceDescription = dialogView.findViewById(R.id.see_service_description);
                TextView servicePrice = dialogView.findViewById(R.id.see_service_price);
                Button appointment = dialogView.findViewById(R.id.see_service_appointment_btn);

                serviceDescription.setText(service.getDescription());
                servicePrice.setText(Double.toString(service.getFee()));

                builder.setTitle(service.getName());

                appointment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // INTENT POUR ADD APPOINTMENT
                        // PUT EXTRA
                    }
                });
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseClinicsServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                serviceSearchList.clear();

                for(DataSnapshot clinicsSnapshot : dataSnapshot.getChildren()) {

                    Clinic clinic = clinicsSnapshot.getValue(Clinic.class);

                    DatabaseReference databaseServices = databaseClinicsServices.child(clinic.getClinicId()).child("services_available");

                    databaseServices.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot servicesSnapshot : dataSnapshot.getChildren()) {

                                Service service = servicesSnapshot.getValue(Service.class);
                                serviceSearchList.add(service);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

                adapter = new ServiceSearchList(SearchServiceActivity.this, serviceSearchList);
                serviceSearchListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Clinic;
import com.a19_21.clinicapp.model.ClinicsList;
import com.a19_21.clinicapp.model.ServiceListEmployee;
import com.a19_21.clinicapp.model.Service;
import com.a19_21.clinicapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private Button createClinicBtn;
    private Button addServiceBtn;
    private Button manageServicesBtn;

    private TextView greetTxt;

    private ListView clinicsListView;
    private List<Clinic> clinicsList;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

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
    private String clID;

    // Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseClinics;
    DatabaseReference databaseClinServices;
    DatabaseReference employeeRef;

    Intent goToAddService;
    Intent goToManageServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        greetTxt = (TextView) findViewById(R.id.activity_employee_greeting_txt);

        clinicsList = new ArrayList<>();
        clinicsListView = (ListView) findViewById(R.id.activity_employee_cliniclistview);

        databaseClinics = database.getReference("clinic");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        employeeRef = database.getReference("user/" + firebaseUser.getUid());

        createClinicBtn = findViewById(R.id.activity_employee_create_clinic_btn);
        addServiceBtn = findViewById(R.id.activity_employee_add_service_btn);
        manageServicesBtn = findViewById(R.id.activity_employee_see_service_btn);

        // SET THE GREETING TEXT FOR EMPLOYEE
        employeeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                greetTxt.setText("Welcome " + user.getUsername() + ", you're connected as " + user.getType());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        // GOES TO CREATE CLINIC
        createClinicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreateClinic = new Intent(EmployeeActivity.this, CreateClinicActivity.class);
                startActivity(goToCreateClinic);
            }
        });

        // DISPLAY THE CLINIC AND SENDS INFO TO EDIT CLINIC
        clinicsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Clinic clinic = clinicsList.get(position);

                final Intent goToEditClinic = new Intent(EmployeeActivity.this, EditClinicActivity.class);

                String employeeID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                goToEditClinic.putExtra("cName", clinic.getClinicName());
                goToEditClinic.putExtra("cPhone", clinic.getPhoneNumber());
                goToEditClinic.putExtra("clID",clinic.getClinicId());

                startActivity(goToEditClinic);

                return true;
            }

        });

        // SENDS TO ADD SERVICE
        addServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToAddService);
            }
        });

        // SENDS TO MANAGE SERVICES
        manageServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToManageServices);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        // DISPLAYS THE EMPLOYEE'S CLINIC

        // Ajouter un Listener à la référence à la base de données
        databaseClinics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                goToAddService = new Intent(EmployeeActivity.this, AddServiceActivity.class);
                goToManageServices = new Intent(EmployeeActivity.this, EmployeeManageServicesActivity.class);

                String employeeID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                clinicsList.clear();

                // Loop à travers les objets enfants de cette référence
                for(DataSnapshot clinicSnapshot : dataSnapshot.getChildren()) {

                    // Les mettre dans un objet de type Clinic
                    Clinic clinic = clinicSnapshot.getValue(Clinic.class);


                    goToAddService.putExtra("clID", clinic.getClinicId());
                    goToManageServices.putExtra("clID",clinic.getClinicId());

                    // Si la clinique appartient à l'employé
                    if (clinic.getEmployeeID().equals(employeeID)) {

                        // Ajouter la clinique à ta liste de cliniques
                        clinicsList.add(clinic);
                    }
                    if(!clinicsList.isEmpty()) {
                        createClinicBtn.setEnabled(false);
                    }
                }

                // Créer une instance de ta classe liste (adapter pour Listview), et y ajouter la liste des cliniques que tu viens de fabriquer
                ClinicsList adapter = new ClinicsList(EmployeeActivity.this, clinicsList);

                // Attacher l'adapteur au listview défini dans onCreate
                clinicsListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
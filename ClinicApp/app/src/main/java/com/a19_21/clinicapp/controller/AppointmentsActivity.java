package com.a19_21.clinicapp.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Appointment;
import com.a19_21.clinicapp.model.AppointmentsList;
import com.a19_21.clinicapp.model.Service;
import com.a19_21.clinicapp.model.ServicesList;
import com.a19_21.clinicapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {
    private DatabaseReference databaseAppointments;
    private ListView appointmentsListView;
    private List<Appointment> appointmentsList;

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
        setContentView(R.layout.activity_appointments);

        databaseAppointments = FirebaseDatabase.getInstance().getReference("appointment");

        appointmentsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Appointment appointment = appointmentsList.get(position);
                deleteAppointment(appointment.getAppointmentId());
                return true;
            }
        });
        appointmentsListView = (ListView) findViewById(R.id.appointments_listview);
        appointmentsList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseAppointments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                appointmentsList.clear();

                for (DataSnapshot appointmentSnapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = appointmentSnapshot.getValue(Appointment.class);
                    appointmentsList.add(appointment);
                }

                AppointmentsList adapter = new AppointmentsList(AppointmentsActivity.this, appointmentsList);
                appointmentsListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void deleteAppointment(String id){

        final String appointmentID = id;

        AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentsActivity.this);
        builder.setTitle("Warning !")
                .setMessage("Are you sure you want to cancel this appointment ? You're not gonna regret ? Like, sure SURE ?")
                .setPositiveButton("FINISH HIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference appointmentToDelete = FirebaseDatabase.getInstance().getReference("appointment").child(appointmentID);
                        appointmentToDelete.removeValue();
                        Toast.makeText(AppointmentsActivity.this, "Appointment deleted",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("MERCY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create()
                .show();
    }
}

package com.a19_21.clinicapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.Appointment;
import com.a19_21.clinicapp.model.Clinic;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        private Activity context;
        private TextView serviceName;
        private TextView serviceDescription;
        private TextView serviceClinic;
        private TextView servicePrice;
        private EditText date;
        private EditText time;
        private Button bookBtn;

        // Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseUsers;
        DatabaseReference databaseClinics;
        DatabaseReference databaseAdresses;
        DatabaseReference databaseAppointments;

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
            setContentView(R.layout.activity_booking);

            date = (EditText) findViewById(R.id.set_date);
            time = (EditText) findViewById(R.id.set_time);
            bookBtn = (Button) findViewById(R.id.bookbtn);
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.search_service_item, null, true);
            TextView serviceName = (TextView) listViewItem.findViewById(R.id.search_service_name);
            TextView serviceDescription = (TextView) listViewItem.findViewById(R.id.search_service_description);
            TextView serviceClinic = listViewItem.findViewById(R.id.search_service_clinic);
            TextView servicePrice = listViewItem.findViewById(R.id.search_service_price);

            databaseUsers = database.getReference("user");
            databaseClinics = database.getReference("clinic");
            databaseAdresses = database.getReference("adress");
            databaseAppointments = database.getReference("appointment");


            bookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createBooking();
                    finish();
                }
            });

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        public void createBooking(){

            // Get user ID to associate him to booking
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            String clinic = serviceClinic.getText().toString().trim();
            String service = serviceName.getText().toString().trim();
            String bookDate = date.getText().toString().trim();
            String bookTime = time.getText().toString().trim();


            if(!TextUtils.isEmpty(bookDate) && !TextUtils.isEmpty(bookTime)) {

                String id = databaseAppointments.push().getKey();

                Appointment appointment = new Appointment(id, clinic, service, bookDate, bookTime, userId);

                databaseAppointments.child(id).setValue(appointment);

                Toast.makeText(this, "Appointment created ^^ !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter a date and time", Toast.LENGTH_SHORT).show();
            }
        }

}

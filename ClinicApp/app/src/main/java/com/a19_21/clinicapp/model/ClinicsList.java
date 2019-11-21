package com.a19_21.clinicapp.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.a19_21.clinicapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ClinicsList extends ArrayAdapter<Clinic> {

    private Activity context;
    private List<Clinic> clinicsList;

    public ClinicsList(Activity context, List<Clinic> clinicsList) {
        super(context, R.layout.list_clinic_item, clinicsList);
        this.context = context;
        this.clinicsList = clinicsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_clinic_item, null, true);

        TextView name = (TextView) listViewItem.findViewById(R.id.list_clinic_name_txt);
        TextView phone = (TextView) listViewItem.findViewById(R.id.list_clinic_phone_txt);
        TextView hours = (TextView) listViewItem.findViewById(R.id.list_clinic_hoursOp_txt);

        Clinic clinic = clinicsList.get(position);

        name.setText(clinic.getClinicName());
        phone.setText(clinic.getPhoneNumber());

        // SET HOURS OF OPERATION

        return listViewItem;
    }
}

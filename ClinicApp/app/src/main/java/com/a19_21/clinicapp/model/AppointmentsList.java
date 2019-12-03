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

import java.util.List;

public class AppointmentsList extends ArrayAdapter<Appointment> {
    private Activity context;
    private List<Appointment> appointmentList;

    public AppointmentsList(Activity context, List<Appointment> appointmentList) {
        super(context, R.layout.list_booking, appointmentList);
        this.context = context;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_booking, null, true);

        TextView clinic = (TextView) listViewItem.findViewById(R.id.list_booking_clinic);
        TextView service = (TextView) listViewItem.findViewById(R.id.list_booking_service);
        TextView date = (TextView) listViewItem.findViewById(R.id.list_booking_date);
        TextView time = (TextView) listViewItem.findViewById(R.id.list_booking_time);


        Appointment appointment = appointmentList.get(position);

        clinic.setText(appointment.getClinic());
        service.setText(appointment.getService());
        date.setText(appointment.getDate().toString());
        time.setText(appointment.getTime().toString());

        return listViewItem;
    }

}

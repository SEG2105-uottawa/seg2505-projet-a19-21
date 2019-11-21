package com.a19_21.clinicapp.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.controller.ManageServicesActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ServiceListClinic extends ArrayAdapter<Service> {

    private Activity context;
    private List<Service> serviceListClinic;

    public ServiceListClinic(Activity context, List<Service> serviceListClinic) {
        super(context, R.layout.list_service_cl_item, serviceListClinic);
        this.context = context;
        this.serviceListClinic = serviceListClinic;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_service_cl_item, null, true);

        TextView serviceName = (TextView) listViewItem.findViewById(R.id.list_service_cl_name);
        TextView serviceDescription = (TextView) listViewItem.findViewById(R.id.list_service_cl_description);

        Service service = serviceListClinic.get(position);

        serviceName.setText(service.getName());
        serviceDescription.setText(service.getDescription());

        return listViewItem;
    }
}

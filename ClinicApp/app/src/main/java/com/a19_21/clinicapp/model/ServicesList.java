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

public class ServicesList extends ArrayAdapter<Service> {

    private Activity context;
    private List<Service> servicesList;

    public ServicesList(Activity context, List<Service> servicesList) {
        super(context, R.layout.list_service_item, servicesList);
        this.context = context;
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_service_item, null, true);

        TextView serviceName = (TextView) listViewItem.findViewById(R.id.list_service_name);
        TextView serviceDescription = (TextView) listViewItem.findViewById(R.id.list_service_description);
        Button editButton = (Button) listViewItem.findViewById(R.id.list_service_edit_btn);
        Button deleteButton = (Button) listViewItem.findViewById(R.id.list_service_delete_btn);

        Service service = servicesList.get(position);

        serviceName.setText(service.getName());
        serviceDescription.setText(service.getDescription());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Service service = servicesList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Warning !")
                        .setMessage("Are you sure you want to delete this service ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteService(service.getServiceId());
                            }
                        })
                        .setNegativeButton("NO, thanks", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();

            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Service service = servicesList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = context.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.update_service_box, null);
                builder.setView(dialogView);

                final EditText serviceName = dialogView.findViewById(R.id.update_service_box_name_input);
                final EditText serviceDescription = dialogView.findViewById(R.id.update_service_box_description_input);
                final Button updateBtn = dialogView.findViewById(R.id.update_service_box_update_btn);

                builder.setTitle(service.getName())
                        .create()
                        .show();

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = serviceName.getText().toString().trim();
                        String description = serviceDescription.getText().toString().trim();

                        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description)) {
                            updateService(service.getServiceId(), name, description);
                        }
                    }
                });
            }
        });

        return listViewItem;
    }

    public void updateService(String id, String name, String description) {
        DatabaseReference oldService = FirebaseDatabase.getInstance().getReference("service").child(id);
        Service newService = new Service(id, name, description);

        oldService.setValue(newService);

        Toast.makeText(getContext(), "Service updated", Toast.LENGTH_SHORT).show();

    }

    public void deleteService(String id) {

        DatabaseReference serviceToDelete = FirebaseDatabase.getInstance().getReference("service").child(id);
        serviceToDelete.removeValue();

        Toast.makeText(getContext(), "Service deleted", Toast.LENGTH_SHORT).show();
    }
}

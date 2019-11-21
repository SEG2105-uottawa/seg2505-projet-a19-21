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

public class ServiceListEmployee extends ArrayAdapter<Service> {

    private Activity context;
    private List<Service> serviceListEmployee;

    public ServiceListEmployee(Activity context, List<Service> serviceListEmployee) {
        super(context, R.layout.list_service_item, serviceListEmployee);
        this.context = context;
        this.serviceListEmployee = serviceListEmployee;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_services_emp_item, null, true);

        TextView serviceName = (TextView) listViewItem.findViewById(R.id.list_services_emp_name);
        TextView serviceDescription = (TextView) listViewItem.findViewById(R.id.list_services_emp_description);
        TextView servicePrice = (TextView) listViewItem.findViewById(R.id.list_services_emp_price);

        Service service = serviceListEmployee.get(position);


        serviceName.setText(service.getName());
        serviceDescription.setText(service.getDescription());
        servicePrice.setText(Double.toString(service.getFee()));

        return listViewItem;
    }
}
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

public class ServiceSearchList extends ArrayAdapter<Service> {

    private Activity context;
    private List<Service> serviceSearchList;

    public ServiceSearchList(Activity context, List<Service> serviceSearchList) {
        super(context, R.layout.list_service_cl_item, serviceSearchList);
        this.context = context;
        this.serviceSearchList = serviceSearchList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.search_service_item, null, true);

        TextView serviceName = (TextView) listViewItem.findViewById(R.id.search_service_name);
        TextView serviceDescription = (TextView) listViewItem.findViewById(R.id.search_service_description);
        TextView serviceClinic = listViewItem.findViewById(R.id.search_service_clinic);
        TextView servicePrice = listViewItem.findViewById(R.id.search_service_price);

        Service service = serviceSearchList.get(position);

        serviceName.setText(service.getName());
        serviceDescription.setText(service.getDescription());
        servicePrice.setText("Price : " + service.getFee());

        return listViewItem;
    }
}

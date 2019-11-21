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

public class UsersList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public UsersList(Activity context, List<User> userList) {
        super(context, R.layout.list_item, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView username = (TextView) listViewItem.findViewById(R.id.list_item_username);
        TextView accountType = (TextView) listViewItem.findViewById(R.id.list_item_account_type);
        TextView email = (TextView) listViewItem.findViewById(R.id.list_item_email);

        User user = userList.get(position);

        username.setText(user.getUsername());
        accountType.setText(user.getType());
        email.setText(user.getEmail());

        return listViewItem;
    }
}

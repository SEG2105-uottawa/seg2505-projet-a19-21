package com.a19_21.clinicapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a19_21.clinicapp.R;
import com.a19_21.clinicapp.model.User;
import com.a19_21.clinicapp.model.UsersList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageUsersActivity extends AppCompatActivity {

    private ListView usersListView;
    private List<User> usersList;

    private DatabaseReference usersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        usersList = new ArrayList<>();
        usersListView = (ListView) findViewById(R.id.manage_users_userslistview);
        usersDatabase = FirebaseDatabase.getInstance().getReference("user");

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManageUsersActivity.this);

                builder.setTitle("Warning !");
                builder.setMessage("Are you sure you want to delete this user ? You're not gonna regret ? Like, sure SURE ?");
                builder.setPositiveButton("FINISH HIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // DELETE HIM
                    }
                });
                builder.setNegativeButton("MERCY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // RETURN TO MANAGE USERS
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        usersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usersList.clear();
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    User user = userSnapshot.getValue(User.class);
                    usersList.add(user);
                }
                UsersList adapter = new UsersList(ManageUsersActivity.this, usersList);
                usersListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

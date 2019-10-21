package com.a19_21.clinicapp.model;

import androidx.annotation.NonNull;

public class User {

    private String username;
    private String email;
    private String password;
    private String id;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int accountID; // get from Firebase
    //private TimeStamp creationDate;

    public User(String id, String username, String email, String password, String type){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        // this.creationDate = get current time and store it
    }

    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "Username : " + username + " Type : " + type;
    }
}

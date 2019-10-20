package com.a19_21.clinicapp.model;

public class User {

    private String username;
    private String email;
    private String password;

    private int accountID; // get from Firebase
    //private TimeStamp creationDate;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        // this.creationDate = get current time and store it
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
}

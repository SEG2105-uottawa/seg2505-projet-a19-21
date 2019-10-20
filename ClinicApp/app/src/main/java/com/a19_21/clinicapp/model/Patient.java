package com.a19_21.clinicapp.model;

public class Patient extends User{

    private int nbReviews;

    public Patient(String id, String username, String email, String password) {
        super(id , username, email, password);
    }
}

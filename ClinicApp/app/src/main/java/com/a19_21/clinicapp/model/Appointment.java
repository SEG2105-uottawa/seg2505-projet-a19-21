package com.a19_21.clinicapp.model;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    private String id;
    private String clinic;
    private String service;
    private String date;
    private String time;
    private String userId;

    public Appointment(String id, String clinic, String service, String date, String time, String userID) {
        this.id = id;
        this.clinic = clinic;
        this.service = service;
        this.date = date;
        this.time = time;
        this.userId = userID;
    }

    public String getClinic() {
        return clinic;
    }

    public String getAppointmentId() {
        return id;
    }

    public void setAppointmentId(String id) {
        this.id = id;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return ("Clinic : " + clinic + "  Service : " + service + " Date :" + date + " Time : " + time);
    }

}

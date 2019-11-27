package com.a19_21.clinicapp.model;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    private String id;
    private String clinic;
    private String service;
    private Date date;
    private Time time;

    private Clinic[] clinicList;

    public Appointment(String id, String clinic, String service, Date date, Time time) {
        this.id = id;
        this.clinic = clinic;
        this.service = service;
        this.date = date;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return ("Clinic : " + clinic + "  Service : " + service + " Date :" + date + " Time : " + time);
    }

}

package com.a19_21.clinicapp.model;

import java.sql.Time;

public class Clinic {

    private String clinicId;
    private String clinicName;
    private String phoneNumber;

    private String selfEmployeeID;
    private String selfAdressID;
    //private rating;


    private String services;
    private String hours;
    private String reviews;


    public Clinic(String id, String clinicName, String employeeID){
        this.clinicId = id;
        this.clinicName = clinicName;
        this.selfEmployeeID = employeeID;
        this.services = "";
        this.hours = "";
        this.reviews = "";
    }

    public Clinic() {}

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String id) {
        this.clinicId = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdressID() {
        return selfAdressID;
    }

    public void setAdressID(String adressID) {
        this.selfAdressID = adressID;
    }

    public String getEmployeeID() {
        return selfEmployeeID;
    }

    public void setEmployeeID(String selfEmployeeID) {
        this.selfEmployeeID = selfEmployeeID;
    }

    /*

    // --------------------------- SET HOURS OF OPERATION --------------------------------- //

    public void setMondayHours(Time start, Time close) {
        this.startHours[0] = start;
        this.closeHours[0] = close;
    }

    public void setTuesdayHours(Time start, Time close) {
        this.startHours[1] = start;
        this.closeHours[1] = close;
    }

    public void setWednesdayHours(Time start, Time close) {
        this.startHours[2] = start;
        this.closeHours[2] = close;
    }

    public void setThursdayHours(Time start, Time close) {
        this.startHours[3] = start;
        this.closeHours[3] = close;
    }

    public void setFridayHours(Time start, Time close) {
        this.startHours[4] = start;
        this.closeHours[4] = close;
    }

    public void setSaturdayHours(Time start, Time close) {
        this.startHours[5] = start;
        this.closeHours[5] = close;
    }

    public void setSundayHours(Time start, Time close) {
        this.startHours[6] = start;
        this.closeHours[6] = close;
    }

    // -------------------------------------------------------------------------------------//

     */
}

package com.a19_21.clinicapp.model;

public class Clinic {

    private String id;
    private String clinicName;
    private String phoneNumber;
    private String hoursOfOperation;

    private Adress adress;

    /*
    private Service[] services;
    private Review[] reviews;
     */

    public Clinic(String id, String clinicName, String phoneNumber, String hoursOfOperation){
        this.id = id;
        this.clinicName = clinicName;
        this.phoneNumber = phoneNumber;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

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

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }
}

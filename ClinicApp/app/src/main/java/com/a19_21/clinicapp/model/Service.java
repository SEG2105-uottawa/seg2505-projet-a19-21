package com.a19_21.clinicapp.model;

public class Service {

    private String id;
    private String name;
    private String description;
    private double fee;

    private Clinic[] clinicList;

    public Service(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = 0;
    }

    public Service(){}

    public String getName() {
        return name;
    }

    public String getServiceId() {
        return id;
    }

    public void setServiceId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Clinic[] getClinicList() {
        return clinicList;
    }

    public String toString() {
        return ("Name : " + name + "  Description : " + description);
    }

}

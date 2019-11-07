package com.a19_21.clinicapp.model;

public class Adress {

    private int streetNumber;
    private String streetName;
    private String cityAndProvince;
    private String postalCode;

    public Adress(int streetNumber, String streetName, String cityAndProvince, String postalCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.cityAndProvince = cityAndProvince;
        this.postalCode = postalCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityAndProvince() {
        return cityAndProvince;
    }

    public void setCityAndProvince(String cityAndProvince) {
        this.cityAndProvince = cityAndProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

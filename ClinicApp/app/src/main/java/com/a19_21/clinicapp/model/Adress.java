package com.a19_21.clinicapp.model;

public class Adress {

    private String adressId;
    private String streetNbAndName;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public Adress(String adressId, String streetNbAndName, String city) {
        this.adressId = adressId;
        this.streetNbAndName = streetNbAndName;
        this.city = city;
    }

    public String getAdressId() { return adressId; }

    public void setAdressId(String adressId) { this.adressId = adressId; }

    public String getStreetNbAndName() {
        return streetNbAndName;
    }

    public void setStreetNbAndName(String streetNbAndName) { this.streetNbAndName = streetNbAndName; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) { this.city = city; }

    public String getProvince() { return province; }

    public void setProvince(String province) { this.province = province; }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }
}

package com.example.empresadis;

public class Localization {
    private String Direc, City, Country;

    public Localization(){

    }

    public Localization(String direc, String city, String country) {
        Direc = direc;
        City = city;
        Country = country;
    }

    public String getAddress() {
        return Direc;
    }
    public String getCity() {
        return City;
    }
    public String getCountry() {
        return Country;
    }
}

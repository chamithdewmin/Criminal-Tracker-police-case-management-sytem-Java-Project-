package com.example.crimejava.Models;

public class CaseMap {

    private String caseNo;
    private double latitude;
    private double longitude;
    private String location;

    public CaseMap(String caseNo, double latitude, double longitude, String location) {
        this.caseNo = caseNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }
}

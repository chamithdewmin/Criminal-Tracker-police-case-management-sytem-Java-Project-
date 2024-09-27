package com.example.crimejava.Models;

public class Accident {

    private String id;
    private String accidentDate;
    private String location;
    private String accidentType;
    private String weatherConditions;
    private String caseStatus;
    private String description;

    public Accident(String id, String accidentDate, String location, String accidentType, String weatherConditions, String description, String caseStatus) {
        this.id = id;
        this.accidentDate = accidentDate;
        this.location = location;
        this.accidentType = accidentType;
        this.weatherConditions = weatherConditions;
        this.description = description;
        this.caseStatus = caseStatus;
    }

    public String getId() {
        return id;
    }

    public String getAccidentDate() {
        return accidentDate;
    }

    public String getLocation() {
        return location;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public String getWeatherConditions() {
        return weatherConditions;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public String getDescription() {
        return description;
    }
}

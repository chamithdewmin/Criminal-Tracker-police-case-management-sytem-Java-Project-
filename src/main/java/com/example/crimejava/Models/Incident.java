package com.example.crimejava.Models;

public class Incident {

    private String complaint_id2;
    private String location_of_incident;
    private String incident_date;
    private String incident_nature;
    private String how_reported;
    private String district_area;
    private String description;

    public Incident(String complaint_id2, String incident_nature, String incident_date, String how_reported, String location_of_incident, String district_area, String description) {
        this.complaint_id2 = complaint_id2;
        this.incident_nature = incident_nature;
        this.incident_date = incident_date;
        this.how_reported = how_reported;
        this.location_of_incident = location_of_incident;
        this.district_area = district_area;
        this.description = description;
    }


    public String getComplaint_id2() {
        return complaint_id2;
    }

    public String getIncident_nature() {
        return incident_nature;
    }

    public String getIncident_date() {
        return incident_date;
    }

    public String getHow_reported() {
        return how_reported;
    }

    public String getLocation_of_incident() {
        return location_of_incident;
    }

    public String getDistrict_area() {
        return district_area;
    }

    public String getDescription() {
        return description;
    }
}

package com.example.crimejava.Models;

public class PartiesAndOfficers {

    private String accidentID;  // Added field
    private String fullName;
    private String nic;
    private String gender;
    private String contactNumber;
    private String email;
    private String city;
    private String officerName;
    private String badgeNumber;
    private String policeStation;

    public PartiesAndOfficers(String accidentID, String fullName, String nic, String gender, String contactNumber, String email, String city, String officerName, String badgeNumber, String policeStation) {

        this.accidentID = accidentID;  // Initialize accidentID
        this.fullName = fullName;
        this.nic = nic;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.email = email;
        this.city = city;
        this.officerName = officerName;
        this.badgeNumber = badgeNumber;
        this.policeStation = policeStation;
    }

    public String getAccidentID() {
        return accidentID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNic() {
        return nic;
    }

    public String getGender() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getOfficerName() {
        return officerName;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public String getPoliceStation() {
        return policeStation;
    }
}

package com.example.crimejava.Models;

public class AdminAccounts {

    private String adminId;
    private String adminOfficerFName;
    private String adminOfficerLName;
    private String adminOfficerBadgeNumber;
    private String adminOfficerEmail;
    private String adminUsername;

    // Constructor
    public AdminAccounts(String id, String officerFName, String officerLName, String officerBadgeNumber, String officerEmail, String username) {
        this.adminId = id;
        this.adminOfficerFName = officerFName;
        this.adminOfficerLName = officerLName;
        this.adminOfficerBadgeNumber = officerBadgeNumber;
        this.adminOfficerEmail = officerEmail;
        this.adminUsername = username;
    }

    // Getters
    public String getAdminId() {
        return adminId;
    }

    public String getAdminOfficerFName() {
        return adminOfficerFName;
    }

    public String getAdminOfficerLName() {
        return adminOfficerLName;
    }

    public String getAdminOfficerBadgeNumber() {
        return adminOfficerBadgeNumber;
    }

    public String getAdminOfficerEmail() {
        return adminOfficerEmail;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    // Setters
    public void setadminId(String adminId) {
        this.adminId = adminId;
    }

    public void setadminOfficerFName(String adminOfficerFName) {
        this.adminOfficerFName = adminOfficerFName;
    }

    public void setadminOfficerLName(String adminOfficerLName) {
        this.adminOfficerLName = adminOfficerLName;
    }

    public void setadminOfficerBadgeNumber(String adminOfficerBadgeNumber) {
        this.adminOfficerBadgeNumber = adminOfficerBadgeNumber;
    }

    public void setadminOfficerEmail(String adminOfficerEmail) {
        this.adminOfficerEmail = adminOfficerEmail;
    }

    public void setadminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
}

package com.example.crimejava.Models;

public class UserAccounts {

    private String userId;
    private String userOfficerFName;
    private String userOfficerLName;
    private String userOfficerBadgeNumber;
    private String userOfficerEmail;
    private String userUsername;

    // Constructor
    public UserAccounts(String id, String officerFName, String officerLName, String officerBadgeNumber, String officerEmail, String username) {
        this.userId = id;
        this.userOfficerFName = officerFName;
        this.userOfficerLName = officerLName;
        this.userOfficerBadgeNumber = officerBadgeNumber;
        this.userOfficerEmail = officerEmail;
        this.userUsername = username;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getUserOfficerFName() {
        return userOfficerFName;
    }

    public String getUserOfficerLName() {
        return userOfficerLName;
    }

    public String getUserOfficerBadgeNumber() {
        return userOfficerBadgeNumber;
    }

    public String getUserOfficerEmail() {
        return userOfficerEmail;
    }

    public String getUserUsername() {
        return userUsername;
    }

    // Setters
    public void setuserId(String userId) {
        this.userId = userId;
    }

    public void setuserOfficerFName(String userOfficerFName) {
        this.userOfficerFName = userOfficerFName;
    }

    public void setuserOfficerLName(String userOfficerLName) {
        this.userOfficerLName = userOfficerLName;
    }

    public void setuserOfficerBadgeNumber(String userOfficerBadgeNumber) {
        this.userOfficerBadgeNumber = userOfficerBadgeNumber;
    }

    public void setuserOfficerEmail(String userOfficerEmail) {
        this.userOfficerEmail = userOfficerEmail;
    }

    public void setuserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}

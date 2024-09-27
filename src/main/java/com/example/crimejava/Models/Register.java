package com.example.crimejava.Models;

public class Register {

    private Integer id;
    private String gender;
    private String firstName;
    private String lastName;
    private String badgeNumber;
    private String email;

    // Constructor to initialize fields
    public Register(int id, String firstName, String gender, String lastName, String badgeNumber, String email) {
        this.id = id;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.badgeNumber = badgeNumber;
        this.email = email;
    }

    // Getter for id
    public Integer getId() {
        return id;
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Getter for badge number
    public String getBadgeNumber() {
        return badgeNumber;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }
}

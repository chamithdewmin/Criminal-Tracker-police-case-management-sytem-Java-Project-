package com.example.crimejava.Models;

public class Compalints {

    private String complaint_id;
    private String full_name;
    private String contact_number;
    private String nic;
    private String gender;
    private String dob;
    private String address;
    private String city_state_zip;
    private int age;
    private String email;

    public Compalints(String complaint_id, String full_name, String contact_number, String nic, String gender, String dob, String city_state_zip, String address, int age, String email) {
        this.complaint_id = complaint_id;
        this.full_name = full_name;
        this.contact_number = contact_number;
        this.nic = nic;
        this.gender = gender;
        this.dob = dob;
        this.city_state_zip = city_state_zip;
        this.address = address;
        this.age = age;
        this.email = email;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getNic() {
        return nic;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getCity_state_zip() {
        return city_state_zip;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}

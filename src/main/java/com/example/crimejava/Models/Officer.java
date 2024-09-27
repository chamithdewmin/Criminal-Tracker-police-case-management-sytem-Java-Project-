package com.example.crimejava.Models;

public class Officer {
    private String officerCaseNo;
    private String officerName;
    private String OfficerBadgeNumber;
    private String PoliceStation;
    private String criminalName;
    private String criminalNic;
    private String crimeDate;

    // Constructor
    public Officer(String officercaseNo, String officername, String officerBadgeNumber, String policeStation, String criminalName, String criminalNic, String crimeDate) {
        this.officerCaseNo = officercaseNo;
        this.officerName = officername;
        this.OfficerBadgeNumber = officerBadgeNumber;
        this.PoliceStation = policeStation;
        this.criminalName = criminalName;
        this.criminalNic = criminalNic;
        this.crimeDate = crimeDate;
    }


    // Getters and setters
    public String getOfficerCaseNo() {return officerCaseNo; }
    public String getOfficerName() { return officerName; }
    public String getOfficerBadgeNumber() { return OfficerBadgeNumber; }
    public String getPoliceStation() { return PoliceStation; }
    public String getCriminalName() { return criminalName; }
    public String getCriminalNic() { return criminalNic; }
    public String getCrimeDate() { return crimeDate; }
}
package com.example.crimejava.Models;

public class Case {
    private String caseNo;
    private String caseType;
    private String caseSubType;
    private String stageOfCase;
    private String stage;
    private String location;
    private String filingNumber;
    private String filingDate;
    private String firstHearingDate;
    private String description;
    private String caseStatus;

    // Constructor
    public Case(String caseNo, String caseType, String caseSubType, String stageOfCase,
                String stage, String location, String filingNumber, String filingDate,
                String firstHearingDate, String description, String caseStatus) {
        this.caseNo = caseNo;
        this.caseType = caseType;
        this.caseSubType = caseSubType;
        this.stageOfCase = stageOfCase;
        this.stage = stage;
        this.location = location;
        this.filingNumber = filingNumber;
        this.filingDate = filingDate;
        this.firstHearingDate = firstHearingDate;
        this.description = description;
        this.caseStatus = caseStatus;
    }



    // Getters and setters
    public String getCaseNo() { return caseNo; }
    public String getCaseType() { return caseType; }
    public String getCaseSubType() { return caseSubType; }
    public String getStageOfCase() { return stageOfCase; }
    public String getStage() { return stage; }
    public String getLocation() { return location; }
    public String getFilingNumber() { return filingNumber; }
    public String getFilingDate() { return filingDate; }
    public String getFirstHearingDate() { return firstHearingDate; }
    public String getDescription() { return description; }
    public String getCaseStatus() { return caseStatus; }
}
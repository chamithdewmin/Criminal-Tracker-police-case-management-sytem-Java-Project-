package com.example.crimejava.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DatabaseDriver {

    private Connection conn;
    private PreparedStatement statement;

    public DatabaseDriver() {
        try {
            // Corrected JDBC URL format
            this.conn = getConnection("jdbc:mysql://localhost:3306/criminaltracker", "root", "mysql@#!132");
            System.out.println("Database connected successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed!");
        }
    }

    // User Login
    public ResultSet getUserData(String username, String password) {
        String query = "SELECT * FROM registertable WHERE username = ?";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedHash = resultSet.getString("password");
                // Compare the entered password with the stored hash
                if (Model.getInstance().compareHash(password, storedHash)) {
                    return resultSet; // Return the user data if password matches
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete User Data
    public boolean deleteUserDataById(String username) {
        String query = "DELETE FROM registertable WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, username);

            int deletedRows = preparedStatement.executeUpdate();
            return deletedRows > 0; // Returns true if the deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUserDataFromAdminById(String id, String newFirstName, String newLastName, String newBadgeNumber, String newEmail) {
        String query = "UPDATE registertable SET fname = ?, lname = ?, badgeNumber = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            // Correcting the order of parameters to match the query
            preparedStatement.setString(1, newFirstName);  // firstName
            preparedStatement.setString(2, newLastName);   // lastName
            preparedStatement.setString(3, newBadgeNumber);  // badgeNumber
            preparedStatement.setString(4, newEmail);  // email
            preparedStatement.setString(5, id);  // id

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateUserDataFromUser(String username, String newEmail, String newFirstName, String newLastName, String newBadgeNumber, String newPassword, String newGender) {
        String query = "UPDATE registertable SET fname = ?, lname = ?, badgeNumber = ?, email = ?, password = ?, gender = ? WHERE username = ?";
        try {

            PreparedStatement preparedStatement = this.conn.prepareStatement(query);

            // Set the values for each placeholder in the query
            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newLastName);
            preparedStatement.setString(3, newBadgeNumber);
            preparedStatement.setString(4, newEmail);
            preparedStatement.setString(5, newPassword);
            preparedStatement.setString(6, newGender);
            preparedStatement.setString(7, username);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Admin Login
    public ResultSet getAdminData(String username, String password) {
        String query = "SELECT * FROM AdminTable WHERE adminusername = ?";
        try { PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String storedHash = resultSet.getString("password");
                    // Compare the entered password with the stored hash
                    if (Model.getInstance().compareHash(password, storedHash)) {
                        return resultSet; // Return the admin data if password matches
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if login fails
    }



    public boolean insertData(String fname, String lname, String email, String gender, String badgeNumber, String username, String password) {
        PreparedStatement insertStatement = null;

        try {

            String query = "INSERT INTO registertable (fname, lname, email, gender, badgeNumber, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            insertStatement = this.conn.prepareStatement(query);

            // Setting the values for the query parameters
            insertStatement.setString(1, fname);
            insertStatement.setString(2, lname);
            insertStatement.setString(3, email);
            insertStatement.setString(4,gender);
            insertStatement.setString(5,badgeNumber);
            insertStatement.setString(6, username);
            insertStatement.setString(7, password);

            // Execute the update and check if any rows were affected
            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close the prepared statement to free resources
            if (insertStatement != null) {
                try {
                    insertStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;

    }

    public boolean isUsernameExists(String username) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM registertable WHERE username = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean officerDetails(String officername, String officerBadgeNumber, String policeStation, String criminalName, String criminalNic, String crimedate) {
        String query = "INSERT INTO officerDetails (officername, OfficerBadgeNumber, policeStation, criminalName, criminalNic, crimedate) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = this.conn.prepareStatement(query)) {
            // Setting the values for the query parameters
            insertStatement.setString(1, officername);
            insertStatement.setString(2, officerBadgeNumber);
            insertStatement.setString(3, policeStation);
            insertStatement.setString(4, criminalName);
            insertStatement.setString(5, criminalNic);
            insertStatement.setString(6, crimedate);

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }


    public boolean CaseDetails(String caseType, String caseSubType, String stageOfCase, String priority, String location,
                               String filingNumber, String filingDate, String firstHearingDate, String description, String caseStatus) {
        try {
            String query = "INSERT INTO caseDetails (caseType, caseSubType, stageOfCase, stage, location, filingNumber, filingDate, " +
                    "firstHearingDate, description, caseStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = this.conn.prepareStatement(query);

            // Setting the values for the query parameters
            insertStatement.setString(1, caseType);
            insertStatement.setString(2, caseSubType);
            insertStatement.setString(3, stageOfCase);
            insertStatement.setString(4, priority);
            insertStatement.setString(5, location);
            insertStatement.setString(6, filingNumber);
            insertStatement.setString(7, filingDate);
            insertStatement.setString(8, firstHearingDate);
            insertStatement.setString(9, description);
            insertStatement.setString(10, caseStatus);

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public ObservableList<Officer> getOfficerDetails() {
        ObservableList<Officer> officers = FXCollections.observableArrayList();
        String query = "SELECT * FROM officerDetails";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                officers.add(new Officer(
                        rs.getString("officercaseNo"),
                        rs.getString("officername"),
                        rs.getString("OfficerBadgeNumber"),
                        rs.getString("policeStation"),
                        rs.getString("criminalName"),
                        rs.getString("criminalNic"),
                        rs.getString("crimedate")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving officer details: " + e.getMessage());
            e.printStackTrace();
        }
        return officers;
    }

    public ObservableList<Case> getCaseDetails() {
        ObservableList<Case> cases = FXCollections.observableArrayList();
        String query = "SELECT * FROM caseDetails";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                cases.add(new Case(
                        rs.getString("caseNo"),
                        rs.getString("caseType"),
                        rs.getString("caseSubType"),
                        rs.getString("stageOfCase"),
                        rs.getString("stage"),
                        rs.getString("location"),
                        rs.getString("filingNumber"),
                        rs.getString("filingDate"),
                        rs.getString("firstHearingDate"),
                        rs.getString("description"),
                        rs.getString("caseStatus")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving case details: " + e.getMessage());
            e.printStackTrace();
        }
        return cases;
    }



    //Search by Case No
    public ObservableList<Case> searchCaseByNo(String caseNo) throws SQLException {
        ObservableList<Case> caseList = FXCollections.observableArrayList();
        String query = "SELECT * FROM caseDetails WHERE caseNo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, caseNo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Case caseData = new Case(
                            rs.getString("caseNo"),
                            rs.getString("caseType"),
                            rs.getString("caseSubType"),
                            rs.getString("stageOfCase"),
                            rs.getString("stage"),
                            rs.getString("location"),
                            rs.getString("filingNumber"),
                            rs.getString("filingDate"),
                            rs.getString("firstHearingDate"),
                            rs.getString("description"),
                            rs.getString("caseStatus")
                    );
                    caseList.add(caseData);
                }
            }
        }
        return caseList;
    }

    //Search by Officer case number
    public ObservableList<Officer> searchOfficerByCaseNo(String OfficercaseNo) throws SQLException {
        ObservableList<Officer> officerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM officerDetails WHERE officercaseNo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, OfficercaseNo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Officer officerData = new Officer(

                            rs.getString("officercaseNo"),  // Ensure column name matches exactly
                            rs.getString("officername"),
                            rs.getString("OfficerBadgeNumber"),
                            rs.getString("policeStation"),
                            rs.getString("criminalName"),
                            rs.getString("criminalNic"),
                            rs.getString("crimedate")
                    );

                    officerList.add(officerData);
                }
            }
        }
        return officerList;
    }

    //Search by Officer badge Number
    public ObservableList<Officer> searchOfficerByBadgeNumber(String OfficerBadgeNumber) throws SQLException {
        ObservableList<Officer> officerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM officerDetails WHERE OfficerBadgeNumber = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, OfficerBadgeNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Officer officerData = new Officer(
                            rs.getString("officercaseNo"),  // Ensure column name matches exactly
                            rs.getString("officername"),
                            rs.getString("OfficerBadgeNumber"),
                            rs.getString("policeStation"),
                            rs.getString("criminalName"),
                            rs.getString("criminalNic"),
                            rs.getString("crimedate")
                    );
                    officerList.add(officerData);
                }
            }
        }
        return officerList;
    }

    //Search by criminal Name
    public ObservableList<Officer> searchCriminalByName(String CriminalName) throws SQLException {
        ObservableList<Officer> officerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM officerDetails WHERE criminalName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, CriminalName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Officer officerData = new Officer(
                            rs.getString("officercaseNo"),  // Ensure column name matches exactly
                            rs.getString("officername"),
                            rs.getString("OfficerBadgeNumber"),
                            rs.getString("policeStation"),
                            rs.getString("criminalName"),
                            rs.getString("criminalNic"),
                            rs.getString("crimedate")
                    );
                    officerList.add(officerData);
                }
            }
        }
        return officerList;
    }

    //Search by criminal Nic
    public ObservableList<Officer> searchCriminalByNic(String CriminalNic) throws SQLException {
        ObservableList<Officer> officerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM officerDetails WHERE criminalNic = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, CriminalNic);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Officer officerData = new Officer(
                            rs.getString("officercaseNo"),  // Ensure column name matches exactly
                            rs.getString("officername"),
                            rs.getString("OfficerBadgeNumber"),
                            rs.getString("policeStation"),
                            rs.getString("criminalName"),
                            rs.getString("criminalNic"),
                            rs.getString("crimedate")
                    );
                    officerList.add(officerData);
                }
            }
        }
        return officerList;
    }

    // Search by Register ID
    public ObservableList<Register> searchByRegisterId(String Id) throws SQLException {
        ObservableList<Register> RegisterList = FXCollections.observableArrayList();
        String query = "SELECT * FROM registertable WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, Integer.parseInt(Id)); // Use setInt instead of setString
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Register RegisterData = new Register(
                            rs.getInt("id"), // Use getInt for id
                            rs.getString("fname"),
                            rs.getString("gender"),
                            rs.getString("lname"),
                            rs.getString("badgeNumber"),
                            rs.getString("email")
                    );
                    RegisterList.add(RegisterData);
                }
            }
        }
        return RegisterList;
    }


    //Search by Register badge Number
    public ObservableList<Register> searchByRegisterBadgeNumber(String BadgeNumber) throws SQLException {
        ObservableList<Register> RegisterList = FXCollections.observableArrayList();
        String query = "SELECT * FROM registertable WHERE badgeNumber = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, BadgeNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Register RegisterData = new Register(
                            rs.getInt("id"),
                            rs.getString("fname"), rs.getString("gender"),
                            rs.getString("lname"),
                            rs.getString("badgeNumber"),
                            rs.getString("email")
                    );
                    RegisterList.add(RegisterData);
                }
            }
        }
        return RegisterList;
    }

    //Search by location
    public ObservableList<Case> searchCaseByLocation(String location) throws SQLException {
        ObservableList<Case> caseList = FXCollections.observableArrayList();
        String query = "SELECT * FROM caseDetails WHERE location = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, location);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Case caseData = new Case(
                            rs.getString("caseNo"),
                            rs.getString("caseType"),
                            rs.getString("caseSubType"),
                            rs.getString("stageOfCase"),
                            rs.getString("stage"),
                            rs.getString("location"),
                            rs.getString("filingNumber"),
                            rs.getString("filingDate"),
                            rs.getString("firstHearingDate"),
                            rs.getString("description"),
                            rs.getString("caseStatus"));
                    caseList.add(caseData);
                }
            }
        }
        return caseList;
    }

    //Search by crime date
    public ObservableList<Officer> searchOfficerByCrimeDate(String crimeDate) throws SQLException {
        ObservableList<Officer> officerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM officerDetails WHERE crimedate = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, crimeDate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Officer officerData = new Officer(

                            rs.getString("officercaseNo"),  // Ensure column name matches exactly
                            rs.getString("officername"),
                            rs.getString("OfficerBadgeNumber"),
                            rs.getString("policeStation"),
                            rs.getString("criminalName"),
                            rs.getString("criminalNic"),
                            rs.getString("crimedate")
                    );
                    officerList.add(officerData);
                }
            }
        }
        return officerList;
    }

    //Search by case status
    public ObservableList<Case> searchCaseByCaseStatus(String location) throws SQLException {
        ObservableList<Case> caseList = FXCollections.observableArrayList();
        String query = "SELECT * FROM caseDetails WHERE caseStatus = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, location);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Case caseData = new Case(
                            rs.getString("caseNo"),
                            rs.getString("caseType"),
                            rs.getString("caseSubType"),
                            rs.getString("stageOfCase"),
                            rs.getString("stage"),
                            rs.getString("location"),
                            rs.getString("filingNumber"),
                            rs.getString("filingDate"),
                            rs.getString("firstHearingDate"),
                            rs.getString("description"),
                            rs.getString("caseStatus"));
                    caseList.add(caseData);
                }
            }
        }
        return caseList;
    }

    public boolean UpdateCaseStauts(String caseStatus, String CaseNo) {
        try {
            String query = "UPDATE caseDetails SET caseStatus = ? WHERE caseNo = ?";
            PreparedStatement updateStatement = this.conn.prepareStatement(query);

            // Setting the values for the query parameters
            updateStatement.setString(1, caseStatus);
            updateStatement.setString(2, CaseNo);


            int rowsAffected = updateStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Password reset.
    public boolean restPassword(String email, String newPassword) {
        try {

            String query = "UPDATE registertable SET password = ? WHERE email = ?";
            PreparedStatement updateStatement = this.conn.prepareStatement(query);

            // Set the hashed password and email
            updateStatement.setString(1, newPassword); // Set the hashed password
            updateStatement.setString(2, email); // Set the email

            int rowsAffected = updateStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Email Check.
    public boolean isEmailExists(String Email) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM registertable WHERE email = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(query)) {
            stmt.setString(1, Email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    //Accident Reports Data Insert.
    public boolean AddAccident(String DateOfAccident, String Location, String typeOfAccident,
                               String weatherConditions, String caseStauts, String description){

        String query = "INSERT INTO accidentReports (accidentDate, location, accidentType, weatherConditions, caseStatus, description) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement insertStatement = this.conn.prepareStatement(query)) {
            insertStatement.setString(1, DateOfAccident);
            insertStatement.setString(2, Location);
            insertStatement.setString(3, typeOfAccident);
            insertStatement.setString(4, weatherConditions);
            insertStatement.setString(5, caseStauts);
            insertStatement.setString(6, description);

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //Accident Report Data Insert part 2.
    public boolean ADDpartiesAndOfficers(String fullName, String nic, String gender, String contactNumber, String email,
                                         String city, String officerName, String badgeNumber, String policeStation){

        String query = "INSERT INTO partiesAndOfficers (fullName, nic, gender, contactNumber, email, city, officerName, badgeNumber, policeStation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement insertStatement = this.conn.prepareStatement(query)) {
            insertStatement.setString(1, fullName);
            insertStatement.setString(2, nic);
            insertStatement.setString(3, gender);
            insertStatement.setString(4, contactNumber);
            insertStatement.setString(5, email);
            insertStatement.setString(6, city);
            insertStatement.setString(7, officerName);
            insertStatement.setString(8, badgeNumber);
            insertStatement.setString(9, policeStation);

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //Accident Data Show on the Table.
    public ObservableList<Accident> getAccidentDetails() {
        ObservableList<Accident> Accident = FXCollections.observableArrayList();
        String query = "SELECT * FROM accidentReports";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Accident.add(new Accident(
                        rs.getString("id"),  // Ensure column name matches exactly
                        rs.getString("accidentDate"),
                        rs.getString("location"),
                        rs.getString("accidentType"),
                        rs.getString("weatherConditions"),
                        rs.getString("description"),
                        rs.getString("caseStatus")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Accident;
    }

    //Accident Data Show on the Table part 2.
    public ObservableList<PartiesAndOfficers> getPartiesAndOfficers() {
        ObservableList<PartiesAndOfficers> partiesAndOfficers = FXCollections.observableArrayList();
        String query = "SELECT * FROM partiesAndOfficers";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                partiesAndOfficers.add(new PartiesAndOfficers(
                        rs.getString("accidentID"),  // Ensure column name matches exactly
                        rs.getString("fullName"),
                        rs.getString("nic"),
                        rs.getString("gender"),
                        rs.getString("contactNumber"),
                        rs.getString("email"),
                        rs.getString("city"),
                        rs.getString("officerName"),
                        rs.getString("badgeNumber"),
                        rs.getString("policeStation"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partiesAndOfficers;
    }

    //Search by Accident id Table 1
    public ObservableList<Accident> searchAccidentByid(String AccidenID) throws SQLException {
        ObservableList<Accident> AccidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM accidentReports WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, AccidenID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Accident AccidentData = new Accident(
                            rs.getString("id"),
                            rs.getString("accidentDate"),
                            rs.getString("location"),
                            rs.getString("accidentType"),
                            rs.getString("weatherConditions"),
                            rs.getString("description"),
                            rs.getString("caseStatus")
                    );
                    AccidentList.add(AccidentData);
                }
            }
        }
        return AccidentList;
    }

    //Search by Accident id Table 2
    public ObservableList<PartiesAndOfficers> searchAccidentByid2(String AccidenID) throws SQLException {
        ObservableList<PartiesAndOfficers> AccidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM partiesAndOfficers WHERE accidentID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, AccidenID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PartiesAndOfficers AccidentData = new PartiesAndOfficers(

                            rs.getString("accidentID"),  // Ensure column name matches exactly
                            rs.getString("fullName"),
                            rs.getString("nic"),
                            rs.getString("gender"),
                            rs.getString("contactNumber"),
                            rs.getString("email"),
                            rs.getString("city"),
                            rs.getString("officerName"),
                            rs.getString("badgeNumber"),
                            rs.getString("policeStation")
                    );
                    AccidentList.add(AccidentData);
                }
            }
        }
        return AccidentList;
    }

    //Search by Officer badge number
    public ObservableList<PartiesAndOfficers> searchByBadgeNumber(String OfficerBadgeNumber) throws SQLException {
        ObservableList<PartiesAndOfficers> AccidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM partiesAndOfficers WHERE badgeNumber = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, OfficerBadgeNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PartiesAndOfficers officerData = new PartiesAndOfficers(
                            rs.getString("accidentID"),  // Ensure column name matches exactly
                            rs.getString("fullName"),
                            rs.getString("nic"),
                            rs.getString("gender"),
                            rs.getString("contactNumber"),
                            rs.getString("email"),
                            rs.getString("city"),
                            rs.getString("officerName"),
                            rs.getString("badgeNumber"),
                            rs.getString("policeStation")
                    );
                    AccidentList.add(officerData);
                }
            }
        }
        return AccidentList;
    }

    //Search by nic
    public ObservableList<PartiesAndOfficers> searchByNic(String nic) throws SQLException {
        ObservableList<PartiesAndOfficers> AccidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM partiesAndOfficers WHERE nic = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nic);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PartiesAndOfficers AccidentData = new PartiesAndOfficers(
                            rs.getString("accidentID"),  // Ensure column name matches exactly
                            rs.getString("fullName"),
                            rs.getString("nic"),
                            rs.getString("gender"),
                            rs.getString("contactNumber"),
                            rs.getString("email"),
                            rs.getString("city"),
                            rs.getString("officerName"),
                            rs.getString("badgeNumber"),
                            rs.getString("policeStation")
                    );
                    AccidentList.add(AccidentData);
                }
            }
        }
        return AccidentList;
    }

    //Search by location
    public ObservableList<Accident> searchByLocation(String location) throws SQLException {
        ObservableList<Accident> AccidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM accidentReports WHERE location = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, location);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Accident AccidentData = new Accident(
                            rs.getString("id"),
                            rs.getString("accidentDate"),
                            rs.getString("location"),
                            rs.getString("accidentType"),
                            rs.getString("weatherConditions"),
                            rs.getString("description"),
                            rs.getString("caseStatus")
                    );
                    AccidentList.add(AccidentData);
                }
            }
        }
        return AccidentList;
    }

    //Search by Accidnt date
    public ObservableList<Accident> searchByAccidentDate(String AccidentDate) throws SQLException {
        ObservableList<Accident> AccidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM accidentReports WHERE accidentDate = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, AccidentDate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Accident AccidentData = new Accident(
                            rs.getString("id"),
                            rs.getString("accidentDate"),
                            rs.getString("location"),
                            rs.getString("accidentType"),
                            rs.getString("weatherConditions"),
                            rs.getString("description"),
                            rs.getString("caseStatus")
                    );
                    AccidentList.add(AccidentData);
                }
            }
        }
        return AccidentList;
    }

    //Search by case status
    public ObservableList<Accident> searchByCaseStatus(String caseStauts) throws SQLException {
        ObservableList<Accident> AccidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM accidentReports WHERE caseStatus = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, caseStauts);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Accident AccidentData = new Accident(
                            rs.getString("id"),
                            rs.getString("accidentDate"),
                            rs.getString("location"),
                            rs.getString("accidentType"),
                            rs.getString("weatherConditions"),
                            rs.getString("description"),
                            rs.getString("caseStatus")
                    );
                    AccidentList.add(AccidentData);
                }
            }
        }
        return AccidentList;
    }

    public boolean addComplaintInfo(
            String fullName, String contactNumber, String nic, String gender, String dob, String address,
            String city, String age, String email){

        String query = "INSERT INTO Complaints (full_name, contact_number, nic, gender, dob, address, city_state_zip, age, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try( PreparedStatement insertStatement = conn.prepareStatement(query)){
            insertStatement.setString(1, fullName);
            insertStatement.setString(2, contactNumber);
            insertStatement.setString(3, nic);
            insertStatement.setString(4, gender);
            insertStatement.setString(5, dob);
            insertStatement.setString(6, address);
            insertStatement.setString(7, city);
            insertStatement.setInt(8, Integer.parseInt(age));
            insertStatement.setString(9, email);

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addIncidentInfo(
            String location_of_incident, String incident_date, String incident_nature, String how_reported, String district_area, String description) {

        String query = "INSERT INTO incident (location_of_incident, incident_date, incident_nature, how_reported, district_area, description) VALUES (?, ?, ?, ?, ?, ?)";
        try( PreparedStatement insertStatement = conn.prepareStatement(query)){
            insertStatement.setString(1, location_of_incident);
            insertStatement.setString(2, incident_date);
            insertStatement.setString(3, incident_nature);
            insertStatement.setString(4, how_reported);
            insertStatement.setString(5, district_area);
            insertStatement.setString(6, description);

            int rowsAffected = insertStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ObservableList<Compalints> getComplaintDetails() {
        ObservableList<Compalints> Complaints = FXCollections.observableArrayList();
        String query = "SELECT * FROM Complaints";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Complaints.add(new Compalints(
                        rs.getString("complaint_id"),  // Ensure column name matches exactly
                        rs.getString("full_name"),
                        rs.getString("contact_number"),
                        rs.getString("nic"),
                        rs.getString("gender"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("city_state_zip"),
                        rs.getInt("age"),
                        rs.getString("email"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Complaints;
    }


    public ObservableList<Incident> getIncidentDetails() {
        ObservableList<Incident> Incidents = FXCollections.observableArrayList();
        String query = "SELECT * FROM incident";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Incidents.add(new Incident(

                        rs.getString("complaint_id2"),  // Ensure column name matches exactly
                        rs.getString("location_of_incident"),
                        rs.getString("incident_date"),
                        rs.getString("incident_nature"),
                        rs.getString("how_reported"),
                        rs.getString("district_area"),
                        rs.getString("description"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Incidents;
    }

    //Sheatch Compalint By ID
    public ObservableList<Compalints> searchCompalintID(String CompalintID) throws SQLException {
        ObservableList<Compalints> ComplaintList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Complaints WHERE complaint_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, CompalintID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Compalints CompalintData = new Compalints(
                            rs.getString("complaint_id"),  // Ensure column name matches exactly
                            rs.getString("full_name"),
                            rs.getString("contact_number"),
                            rs.getString("nic"),
                            rs.getString("gender"),
                            rs.getString("dob"),
                            rs.getString("address"),
                            rs.getString("city_state_zip"),
                            rs.getInt("age"),
                            rs.getString("email")
                    );
                    ComplaintList.add(CompalintData);
                }
            }
        }
        return ComplaintList;
    }

    //Search bY NIC
    public ObservableList<Compalints> searchByNIC(String NIC) throws SQLException {
        ObservableList<Compalints> ComplaintList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Complaints WHERE nic = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, NIC);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Compalints CompalintData = new Compalints(
                            rs.getString("complaint_id"),  // Ensure column name matches exactly
                            rs.getString("full_name"),
                            rs.getString("contact_number"),
                            rs.getString("nic"),
                            rs.getString("gender"),
                            rs.getString("dob"),
                            rs.getString("address"),
                            rs.getString("city_state_zip"),
                            rs.getInt("age"),
                            rs.getString("email")
                    );
                    ComplaintList.add(CompalintData);
                }
            }
        }
        return ComplaintList;
    }

    //Shearch By Dob
    public ObservableList<Compalints> searchByDob(String dob) throws SQLException {
        ObservableList<Compalints> ComplaintList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Complaints WHERE dob = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1,dob);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Compalints CompalintData = new Compalints(
                            rs.getString("complaint_id"),  // Ensure column name matches exactly
                            rs.getString("full_name"),
                            rs.getString("contact_number"),
                            rs.getString("nic"),
                            rs.getString("gender"),
                            rs.getString("dob"),
                            rs.getString("address"),
                            rs.getString("city_state_zip"),
                            rs.getInt("age"),
                            rs.getString("email")
                    );
                    ComplaintList.add(CompalintData);
                }
            }
        }
        return ComplaintList;
    }

    //Search By Contact Number
    public ObservableList<Compalints> searchByPhoneNumber(String CNumber) throws SQLException {
        ObservableList<Compalints> ComplaintList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Complaints WHERE contact_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, CNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Compalints CompalintData = new Compalints(
                            rs.getString("complaint_id"),  // Ensure column name matches exactly
                            rs.getString("full_name"),
                            rs.getString("contact_number"),
                            rs.getString("nic"),
                            rs.getString("gender"),
                            rs.getString("dob"),
                            rs.getString("address"),
                            rs.getString("city_state_zip"),
                            rs.getInt("age"),
                            rs.getString("email")
                    );
                    ComplaintList.add(CompalintData);
                }
            }
        }
        return ComplaintList;
    }

    //Shearch By ID form Incident Table
    public ObservableList<Incident> searchByID2(String ID) throws SQLException {
        ObservableList<Incident> IncidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM incident WHERE complaint_id2 = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Incident IncidenttData = new Incident(
                            rs.getString("complaint_id2"),
                            rs.getString("incident_nature"),
                            rs.getString("incident_date"),
                            rs.getString("how_reported"),
                            rs.getString("location_of_incident"),
                            rs.getString("district_area"),
                            rs.getString("description")
                    );
                    IncidentList.add(IncidenttData);
                }
            }
        }
        return IncidentList;
    }

    public ObservableList<Incident> searchByDate(String Date) throws SQLException {
        ObservableList<Incident> IncidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM incident WHERE incident_date = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, Date);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Incident IncidenttData = new Incident(
                            rs.getString("complaint_id2"),
                            rs.getString("incident_nature"),
                            rs.getString("incident_date"),
                            rs.getString("how_reported"),
                            rs.getString("location_of_incident"),
                            rs.getString("district_area"),
                            rs.getString("description")
                    );
                    IncidentList.add(IncidenttData);
                }
            }
        }
        return IncidentList;
    }

    public ObservableList<Incident> searchByIncidentLocation(String Location) throws SQLException {
        ObservableList<Incident> IncidentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM incident WHERE location_of_incident = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, Location);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Incident IncidenttData = new Incident(
                            rs.getString("complaint_id2"),
                            rs.getString("incident_nature"),
                            rs.getString("incident_date"),
                            rs.getString("how_reported"),
                            rs.getString("location_of_incident"),
                            rs.getString("district_area"),
                            rs.getString("description")
                    );
                    IncidentList.add(IncidenttData);
                }
            }
        }
        return IncidentList;
    }

    public ObservableList<Register> getRegisterDetails() {
        ObservableList<Register> registerList = FXCollections.observableArrayList();
        String query = "SELECT * FROM registerTable";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                registerList.add(new Register(
                        rs.getInt("id"),
                        rs.getString("fname"), rs.getString("gender"),
                        rs.getString("lname"),
                        rs.getString("badgeNumber"),
                        rs.getString("email"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registerList;
    }

}



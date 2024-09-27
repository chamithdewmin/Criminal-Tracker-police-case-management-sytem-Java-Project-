package com.example.crimejava.Models;

import com.example.crimejava.Views.ViewFactory;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {

    private UserAccounts userAccounts;
    private AdminAccounts adminAccounts;
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private boolean userLoginSuccessFlag;
    private boolean adminLoginSuccessFlag;

    // Private constructor for singleton pattern
    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.userLoginSuccessFlag = false;
        this.adminLoginSuccessFlag = false;
        this.userAccounts = new UserAccounts("", "", "", "", "", "");
        this.adminAccounts = new AdminAccounts("", "", "", "", "", "");
    }

    // Singleton pattern to ensure only one instance of the Model exists
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    // Accessors for ViewFactory and DatabaseDriver
    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    // Evaluates the User credentials and sets account data if successful
    public void evaluateUserCred(String username, String password) {
        ResultSet resultSet = databaseDriver.getUserData(username, password);

        try {
            if (resultSet != null) {
                // Fetch the hashed password from the database and compare
                String hashedPassword = resultSet.getString("password");
                if (compareHash(password, hashedPassword)) {
                    userLoginSuccessFlag = true;
                    setUserAccountData(resultSet);
                } else {
                    userLoginSuccessFlag = false;
                }
            } else {
                userLoginSuccessFlag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Evaluates the Admin credentials and sets account data if successful
    public void evaluateAdminCred(String username, String password) {
        ResultSet resultSet = databaseDriver.getAdminData(username, password);

        try {
            if (resultSet != null) {
                // Fetch the hashed password from the database and compare
                String hashedPassword = resultSet.getString("password");
                if (compareHash(password, hashedPassword)) {
                    adminLoginSuccessFlag = true;
                    setAdminAccountData(resultSet);
                } else {
                    adminLoginSuccessFlag = false;
                }
            } else {
                adminLoginSuccessFlag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Set user account data based on the ResultSet retrieved
    public void setUserAccountData(ResultSet resultSet) throws SQLException {
        this.userAccounts.setuserId(resultSet.getString("id"));
        this.userAccounts.setuserOfficerFName(resultSet.getString("fname"));
        this.userAccounts.setuserOfficerLName(resultSet.getString("lname"));
        this.userAccounts.setuserOfficerEmail(resultSet.getString("email"));
        this.userAccounts.setuserOfficerBadgeNumber(resultSet.getString("badgeNumber"));
        this.userAccounts.setuserUsername(resultSet.getString("username"));
    }

    // Set admin account data based on the ResultSet retrieved
    public void setAdminAccountData(ResultSet resultSet) throws SQLException {
        this.adminAccounts.setadminId(resultSet.getString("adminid"));
        this.adminAccounts.setadminOfficerFName(resultSet.getString("adminfname"));
        this.adminAccounts.setadminOfficerLName(resultSet.getString("adminlname"));
        this.adminAccounts.setadminOfficerEmail(resultSet.getString("adminemail"));
        this.adminAccounts.setadminOfficerBadgeNumber(resultSet.getString("adminbadgeNumber"));
        this.adminAccounts.setadminUsername(resultSet.getString("adminusername"));
    }

    // Getters for account data
    public UserAccounts getUserAccounts() {
        return userAccounts;
    }

    public AdminAccounts getAdminAccount() {
        return adminAccounts;
    }

    // Flags for checking login success
    public boolean getUserLoginSuccessFlag() {
        return userLoginSuccessFlag;
    }

    public boolean getAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }

    // Hashes a plain text password using BCrypt
    public String hashText(String plainText) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(plainText, salt);
    }

    // Compares a plain text password with a hashed password
    public boolean compareHash(String plainText, String hashText) {
        return BCrypt.checkpw(plainText, hashText);
    }

    public boolean isAdmin() {
        return adminLoginSuccessFlag;
    }
}

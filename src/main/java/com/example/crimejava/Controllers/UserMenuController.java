package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import com.example.crimejava.Views.MenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {
    public Button dashboardButton;
    public Button crimeButton;
    public Button mapButton;
    public Button registerButton;
    public Button logoutButton;
    public Button reportButton;
    public Button accidentButton;
    public Button ComplaintsBtn;
    public Button UserBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

        private void addListener() {
            dashboardButton.setOnAction(event -> onDashboard());
            crimeButton.setOnAction(event -> onCrimeCase());
            accidentButton.setOnAction(event -> onAddicentReports());
            mapButton.setOnAction(event -> onMap());
            ComplaintsBtn.setOnAction(event -> onCompalins());
            registerButton.setOnAction(event ->onRegister());
            logoutButton.setOnAction(event -> onLogout());
            reportButton.setOnAction(event -> onAddReport());
            UserBtn.setOnAction(event -> onUser());
            UserBtn.setVisible(false);
            UserBtn.setDisable(true);
        }

        private void onDashboard() {
            Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.DASHBOARD);
        }

        private void onCrimeCase() {
            Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.CRIMECASE);
        }

        private void onAddicentReports() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.ACCIDENTREPORTS);}

        private void onMap() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.TRACKBYMAP);}

        private void onCompalins() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.COMPLAINTS);}

        private void onUser() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.USER);
        }

        private void onRegister(){
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.REGISTER);}

        private void onLogout(){

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.close();
            Model.getInstance().getViewFactory().showLoginWindow();
            Model.getInstance().getUserLoginSuccessFlag();

        }
    private void onAddReport() {
        // Close the current window
        Stage stage = (Stage) reportButton.getScene().getWindow();
        stage.close();

        // Show the report window
        Model.getInstance().getViewFactory().showReportView(); // Assuming there's a method to display the report view
    }
}


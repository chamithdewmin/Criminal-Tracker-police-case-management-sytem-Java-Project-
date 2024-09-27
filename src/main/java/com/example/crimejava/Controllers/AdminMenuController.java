package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import com.example.crimejava.Views.MenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    public Button AdmindashboardButton;
    public Button AdmincrimeButton;
    public Button AdminaccidentButton;
    public Button AdminComplaintsBtn;
    public Button AdminmapButton;
    public Button UserBtn;
    public Button AdminregisterButton;
    public Button AdminlogoutButton;
    public Button AdminreportButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        AdmindashboardButton.setOnAction(event -> onDashboard());
        AdmincrimeButton.setOnAction(event -> onCrimeCase());
        AdminaccidentButton.setOnAction(event -> onAccidentReports());
        AdminmapButton.setOnAction(event -> onMap());
        AdminComplaintsBtn.setOnAction(event -> onComplaints());
        AdminregisterButton.setOnAction(event -> onRegister());
        UserBtn.setOnAction(event -> onUser());
        AdminlogoutButton.setOnAction(event -> onLogout());
        AdminreportButton.setOnAction(event -> onAddReport());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.DASHBOARD);
    }

    private void onCrimeCase() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.CRIMECASE);
    }

    private void onAccidentReports() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.ACCIDENTREPORTS);
    }

    private void onMap() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.TRACKBYMAP);
    }

    private void onComplaints() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.COMPLAINTS);
    }

    private void onRegister() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.REGISTER);
    }

    private void onUser() {
        Model.getInstance().getViewFactory().getMenuItem().set(MenuOption.USER);
    }

    private void onLogout() {
        Stage stage = (Stage) AdminlogoutButton.getScene().getWindow();
        stage.close();
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().getAdminLoginSuccessFlag();
    }

    private void onAddReport() {
        Stage stage = (Stage) AdminreportButton.getScene().getWindow();
        stage.close();
        Model.getInstance().getViewFactory().showReportView();
    }
}

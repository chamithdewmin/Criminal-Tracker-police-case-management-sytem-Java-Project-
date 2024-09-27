package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import com.example.crimejava.Utils.EmailValidator;
import com.example.crimejava.Utils.ShowAlert;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.crimejava.Utils.ShowAlert.showAlert;

public class AddComplainsControllers implements Initializable {

    public TextArea descriptiontxt;
    public DatePicker incidentDatePicker;
    public ComboBox<String> howReportedtxt;
    public TextField locationtxt;
    public TextField emailtxt;
    public TextField agetxt;
    public TextField citytxt;
    public TextField nictxt;
    public TextField numbertxt;
    public DatePicker dobDatePicker;
    public Button CancelBtn;
    public ComboBox<String> incidentcombo;
    public TextField fullNametxt;
    public Button backBtn;
    public Button saveBtn;
    public ComboBox<String> gendercombo;
    public TextField discrictxt;
    public TextField addresstxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveBtn.setOnAction(actionEvent -> onSave());
        backBtn.setOnAction(actionEvent -> onBack());

        incidentcombo.getItems().addAll(
                "Misconduct", "Harassment", "Unprofessional Behavior", "Excessive Force",
                "Negligence", "Violation of Rights", "Corruption", "Discrimination",
                "Abuse of Power", "Failure to Follow Procedure", "Rudeness or Disrespect",
                "Delay in Service", "Other"
        );

        howReportedtxt.getItems().addAll("Phone", "Email", "In-Person");
        gendercombo.getItems().addAll("Male", "Female");
    }

    public void onSave() {
        if (validateInputs()) {
            String fullName = fullNametxt.getText();
            String contactNumber = numbertxt.getText();
            String nic = nictxt.getText();
            String gender = gendercombo.getValue();
            String dob = dobDatePicker.getValue() != null ? dobDatePicker.getValue().toString() : "";
            String address = addresstxt.getText();
            String city = citytxt.getText();
            String age = agetxt.getText();
            String email = emailtxt.getText();

            String incidentDate = incidentDatePicker.getValue() != null ? incidentDatePicker.getValue().toString() : "";
            String location = locationtxt.getText();
            String incidentType = incidentcombo.getValue();
            String reportMethod = howReportedtxt.getValue();
            String district = discrictxt.getText();
            String description = descriptiontxt.getText();

            boolean saveComplaint = Model.getInstance().getDatabaseDriver().addComplaintInfo(
                    fullName, contactNumber, nic, gender, dob, address, city, age, email);

            boolean saveIncident = Model.getInstance().getDatabaseDriver().addIncidentInfo(incidentType,incidentDate,reportMethod,location,district,description);


            if (saveComplaint && saveIncident) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Complaint details saved successfully!");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save complaint details.");
            }
        }
    }

    private void clearFields() {
        descriptiontxt.clear();
        locationtxt.clear();
        fullNametxt.clear();
        nictxt.clear();
        numbertxt.clear();
        emailtxt.clear();
        citytxt.clear();
        addresstxt.clear();
        discrictxt.clear();
        agetxt.clear();

        incidentcombo.getSelectionModel().clearSelection();
        howReportedtxt.getSelectionModel().clearSelection();
        gendercombo.getSelectionModel().clearSelection();
        incidentDatePicker.setValue(null);
        dobDatePicker.setValue(null);
    }

    private boolean validateInputs() {
        // Validate full name
        if (fullNametxt.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Full name is required.");
            return false;
        }

        // Validate NIC
        if (nictxt.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "NIC is required.");
            return false;
        }

        // Validate contact number
        if (!numbertxt.getText().matches("\\d{10}")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Contact number must be 10 digits long.");
            return false;
        }

        // Validate email
        if (emailtxt.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email is required.");
            return false;
        }

        if (!EmailValidator.isValidEmail(emailtxt.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Enter a valid email.");
            return false;
        }

        // Validate location
        if (locationtxt.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Location is required.");
            return false;
        }

        // Validate incident type
        if (incidentcombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Incident type is required.");
            return false;
        }

        // Validate how reported
        if (howReportedtxt.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "How reported is required.");
            return false;
        }

        // Validate gender
        if (gendercombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gender is required.");
            return false;
        }

        // Validate date of birth
        if (dobDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Date of Birth is required.");
            return false;
        }

        // Validate description
        if (descriptiontxt.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Description is required.");
            return false;
        }

        return true;
    }

    public void onBack() {
        BorderPane menuParent = (BorderPane) backBtn.getScene().getRoot();
        // Set the center of the BorderPane to the CrimeCaseView
        menuParent.setCenter(Model.getInstance().getViewFactory().ShowCompalinsView());
    }
}

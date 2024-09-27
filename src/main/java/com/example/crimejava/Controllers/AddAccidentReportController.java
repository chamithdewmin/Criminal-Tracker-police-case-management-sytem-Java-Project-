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

public class AddAccidentReportController implements Initializable {

    public Button cancelBtn;
    public Button saveBtn;
    public TextField accidentIDtxt;
    public TextField Locationtxt;
    public ComboBox accidenttypecombo;
    public ComboBox weatherConditionscombo;
    public TextArea descripition;
    public ComboBox gendercombo;
    public TextField nictxt;
    public TextField fullNametxt;
    public ComboBox caseStautscombo;
    public TextField officerBadgetxt;
    public TextField emailtxt;
    public TextField citytxt;
    public Button backBtn;
    public TextField contactnumtxt;
    public TextField offciernametxt;
    public ComboBox policeStationcombo;
    public DatePicker DateofAccident;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backBtn.setOnAction(actionEvent -> onBack());
        saveBtn.setOnAction(actionEvent -> onSave());


        accidenttypecombo.getItems().addAll(
                "Road Traffic Accidents",
                "  - Car Accidents",
                "  - Motorcycle Accidents",
                "  - Bicycle Accidents",
                "  - Pedestrian Accidents",
                "  - Hit and Run",
                "Workplace Accidents",
                "  - Construction Accidents",
                "  - Machinery Accidents",
                "  - Slips, Trips, and Falls",
                "  - Chemical Exposure",
                "  - Electrocution",
                "Public Place Accidents",
                "  - Slips and Falls",
                "  - Accidents in Parks or Public Buildings",
                "  - Escalator and Elevator Accidents",
                "Home Accidents",
                "  - Fire Accidents",
                "  - Electrical Accidents",
                "  - Drowning or Water-related Accidents",
                "  - Poisoning or Gas Leaks",
                "Industrial Accidents",
                "  - Factory Accidents",
                "  - Explosions",
                "  - Toxic Substance Exposure",
                "Medical or Hospital Accidents",
                "  - Surgical Errors",
                "  - Medication Errors",
                "  - Misdiagnosis or Delayed Diagnosis",
                "Railway Accidents",
                "  - Train Collisions",
                "  - Derailments",
                "  - Passenger Falls",
                "Aviation Accidents",
                "  - Plane Crashes",
                "  - Helicopter Crashes",
                "  - Near Miss Incidents",
                "Natural Disasters",
                "  - Earthquake-related Accidents",
                "  - Flood-related Accidents",
                "  - Landslide-related Incidents",
                "Marine or Watercraft Accidents",
                "  - Boat or Ship Collisions",
                "  - Drowning",
                "  - Capsizing",
                "Fire Accidents",
                "  - Residential Fire",
                "  - Industrial Fire",
                "  - Vehicle Fire",
                "Other Types",
                "  - Animal Attacks",
                "  - Sports-related Injuries",
                "  - Violent Attacks or Assaults"
        );

        weatherConditionscombo.getItems().addAll(
                "Clear",
                "Partly Cloudy",
                "Cloudy",
                "Rainy",
                "Stormy",
                "Snowy",
                "Foggy",
                "Windy",
                "Hail",
                "Thunderstorm",
                "Tornado",
                "Heatwave",
                "Blizzard",
                "Drizzle",
                "Freezing Rain"
        );

        caseStautscombo.getItems().addAll(
                "Open",
                "Closed",
                "Pending"
        );

        gendercombo.getItems().addAll(
                "Male",
                "Female"
        );

        policeStationcombo.getItems().addAll(
        "Western Province",
                "- Colombo",
                "    Colombo Fort Police Station",
                "    Colombo North Police Station",
                "    Colombo South Police Station",
                "    Colombo East Police Station",
                "- Gampaha",
                "    Gampaha Police Station",
                "    Negombo Police Station",
                "    Kadawatha Police Station",
                "    Kalutara",
                "    Kalutara Police Station",
                "    Panadura Police Station",
                "    Central Province",
                "- Kandy",
                "    Kandy Police Station",
                "    Peradeniya Police Station",
                "- Matale",
                "    Matale Police Station",
                "- Nuwara Eliya",
                "    Nuwara Eliya Police Station",
                "Southern Province",
                "- Galle",
                "    Galle Police Station",
                "    Hikkaduwa Police Station",
                "- Matara",
                "    Matara Police Station",
                "- Hambantota",
                "    Hambantota Police Station",
                "Eastern Province",
                "- Batticaloa",
                "    Batticaloa Police Station",
                "- Trincomalee",
                "    Trincomalee Police Station",
                "- Ampara",
                "    Ampara Police Station",
                "Northern Province",
                "- Jaffna",
                "    Jaffna Police Station",
                "- Vavuniya",
                "    Vavuniya Police Station",
                "- Mannar",
                "    Mannar Police Station",
                "North Western Province",
                "- Kurunegala",
                "    Kurunegala Police Station",
                "- Puttalam",
                "    Puttalam Police Station",
                "North Central Province",
                "- Anuradhapura",
                "    Anuradhapura Police Station",
                "- Polonnaruwa",
                "    Polonnaruwa Police Station",
                "Uva Province",
                "- Badulla",
                "    Badulla Police Station",
                "- Monaragala",
                "    Monaragala Police Station",
                "Sabaragamuwa Province",
                "- Ratnapura",
                "    Ratnapura Police Station",
                "- Kegalle",
                "    Kegalle Police Station"
        );


    }

    public void onBack() {
            // Get the current BorderPane from the Scene
            BorderPane menuParent = (BorderPane) backBtn.getScene().getRoot();

            // Set the center of the BorderPane to the CrimeCaseView
            menuParent.setCenter(Model.getInstance().getViewFactory().ShowAccidentReportsView());
        }

    public void onSave() {
        if(AccidentvalidateInputs()){
            String accidentDate = DateofAccident.getValue() != null ? DateofAccident.getValue().toString() : "";
            String location = Locationtxt.getText();
            String accidentType = accidenttypecombo.getValue().toString();
            String weatherConditions = weatherConditionscombo.getValue().toString();
            String caseStauts = caseStautscombo.getValue().toString();
            String description = descripition.getText();

            String fullName = fullNametxt.getText();
            String nic = nictxt.getText();
            String gender = gendercombo.getValue().toString();
            String contactNumber = contactnumtxt.getText();
            String email = emailtxt.getText();
            String city = citytxt.getText();
            String officerName = offciernametxt.getText();
            String badgeNumber = officerBadgetxt.getText();
            String PoliceStation = policeStationcombo.getValue().toString();

            boolean saveAccidents = Model.getInstance().getDatabaseDriver().AddAccident(accidentDate,location,accidentType,weatherConditions,caseStauts,description);
            boolean savePartiesAndOfficers = Model.getInstance().getDatabaseDriver().ADDpartiesAndOfficers(fullName,nic,gender,contactNumber,email,city,officerName,badgeNumber,PoliceStation);

            if(saveAccidents && savePartiesAndOfficers){
                ShowAlert.showAlert(Alert.AlertType.INFORMATION,"Success","Accident details saved successfully!");
                Locationtxt.setText("");
                descripition.setText("");
                fullNametxt.setText("");
                nictxt.setText("");
                contactnumtxt.setText("");
                emailtxt.setText("");
                citytxt.setText("");
                offciernametxt.setText("");
                officerBadgetxt.setText("");

                accidenttypecombo.getSelectionModel().select(-1);
                weatherConditionscombo.getSelectionModel().select(-1);
                caseStautscombo.getSelectionModel().select(-1);
                gendercombo.getSelectionModel().select(-1);
                policeStationcombo.getSelectionModel().select(-1);

                DateofAccident.setValue(null);

            }else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save Accident details.");
            }
        }
    }

    private boolean AccidentvalidateInputs(){

        if (DateofAccident.getValue() == null){
            ShowAlert.showAlert(Alert.AlertType.ERROR,"Error","Accident date is required");
        }

        if (Locationtxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Location is required.");
            return false;
        }

        if (accidenttypecombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Accident type is required.");
            return false;
        }

        if (weatherConditionscombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Weather condition is required.");
            return false;
        }

        if (caseStautscombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Case stsuts is required.");
            return false;
        }

        if (descripition.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Description is required.");
            return false;
        }

        if (fullNametxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Full name is required.");
            return false;
        }

        if (nictxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "NIC is required.");
            return false;
        }

        if (gendercombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gender is required.");
            return false;
        }

        if (contactnumtxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Contact number is required.");
            return false;
        }

        if (emailtxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email is required.");
            return false;
        }

        if(!EmailValidator.isValidEmail(emailtxt.getText())) {
            showAlert(Alert.AlertType.ERROR,"Error","Enter valid email.");
            return false;
        }

        if (citytxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "City & zip code is required.");
            return false;
        }

        if (offciernametxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Officer name is required.");
            return false;
        }

        if (officerBadgetxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Officer badge number is required.");
            return false;
        }

        if (policeStationcombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Police station is required.");
            return false;
        }

        return true;
    }

}


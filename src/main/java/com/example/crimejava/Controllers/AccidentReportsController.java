package com.example.crimejava.Controllers;

import com.example.crimejava.Models.*;
import com.example.crimejava.Utils.DateFormat;
import com.example.crimejava.Utils.ShowAlert;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class AccidentReportsController implements Initializable {

    public TableView<Accident> accidenTable;
    public TableColumn<Accident, String> AccidentID;
    public TableColumn<Accident, String> dateOfAccident;
    public TableColumn<Accident, String> typeOfAccident;
    public TableColumn<Accident, String> weatherConditions;
    public TableColumn<Accident, String> Location;
    public TableColumn<Accident, String> Description;
    public TableColumn<Accident, String> caseStauts;

    public TableView<PartiesAndOfficers> partiesTable;
    public TableColumn<PartiesAndOfficers, String> accident2;
    public TableColumn<PartiesAndOfficers, String> fullName;
    public TableColumn<PartiesAndOfficers, String> gender;
    public TableColumn<PartiesAndOfficers, String> nic;
    public TableColumn<PartiesAndOfficers, String> contactNumber;
    public TableColumn<PartiesAndOfficers, String> email;
    public TableColumn<PartiesAndOfficers, String> city;
    public TableColumn<PartiesAndOfficers, String> officerName;
    public TableColumn<PartiesAndOfficers, String> officerBadgeNumber;
    public TableColumn<PartiesAndOfficers, String> policeStation;
    public TextField searchtxt;
    public Button searchbtn;
    public Button addAccidentBtn;
    public Button refreshBtn;
    public ComboBox<String> combobox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addAccidentBtn.setOnAction(actionEvent -> onAccident());
        refreshBtn.setOnAction(actionEvent -> onRefresh());
        searchbtn.setOnAction(actionEvent -> onSearch());

        combobox.getItems().addAll(
                "Accident ID",
                "Accident Date",
                "NIC",
                "Location",
                "Case Status",
                "Officer Badge Number"
        );

        // Setting up the AccidentTable columns
        AccidentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateOfAccident.setCellValueFactory(new PropertyValueFactory<>("accidentDate"));
        Location.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeOfAccident.setCellValueFactory(new PropertyValueFactory<>("accidentType"));
        weatherConditions.setCellValueFactory(new PropertyValueFactory<>("weatherConditions"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        caseStauts.setCellValueFactory(new PropertyValueFactory<>("caseStatus"));

        // Setting up the PartiesAndOfficersTable columns
        accident2.setCellValueFactory(new PropertyValueFactory<>("accidentID"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        officerName.setCellValueFactory(new PropertyValueFactory<>("officerName"));
        officerBadgeNumber.setCellValueFactory(new PropertyValueFactory<>("badgeNumber"));
        policeStation.setCellValueFactory(new PropertyValueFactory<>("policeStation"));

        // Load data into the tables
        loadAccidentTable();
        loadpartiesTable();

    }

    private void loadAccidentTable() {
        try {
            ObservableList<Accident> AccidentData = Model.getInstance().getDatabaseDriver().getAccidentDetails();
            accidenTable.setItems(AccidentData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadpartiesTable() {
        ObservableList<PartiesAndOfficers> PartiesAndOfficersData = Model.getInstance().getDatabaseDriver().getPartiesAndOfficers();
        partiesTable.setItems(PartiesAndOfficersData);
    }

    private void onAccident() {
        // Get the current BorderPane from the MenuFullController
        BorderPane menuParent = (BorderPane) addAccidentBtn.getScene().getRoot();
        menuParent.setCenter(Model.getInstance().getViewFactory().getAddAccidentReportsView());
    }

    private void onRefresh() {
        loadAccidentTable();
        loadpartiesTable();

        searchtxt.setText("");
        combobox.getSelectionModel().clearSelection();
    }

    public void onSearch() {
        String searchText = searchtxt.getText().trim();  // Retrieve the search text
        String selectedType = combobox.getValue();  // Retrieve the selected search type from the ComboBox

        // Show an error if search text or search type is not provided
        if (searchText.isEmpty() || selectedType == null) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Please enter search text and select a search type.");
            return;
        }

        try {
            switch (selectedType) {
                case "Accident ID":
                    // Search by Accident ID
                    ObservableList<Accident> searchResults = Model.getInstance().getDatabaseDriver().searchAccidentByid(searchText);
                    ObservableList<PartiesAndOfficers> searchResults2 = Model.getInstance().getDatabaseDriver().searchAccidentByid2(searchText);
                    accidenTable.setItems(searchResults);
                    partiesTable.setItems(searchResults2);
                    break;

                case "Accident Date":
                    if(DateFormat.isValidDate(searchText)){
                        ObservableList<Accident> ADateResults1 = Model.getInstance().getDatabaseDriver().searchByAccidentDate(searchText);
                        accidenTable.setItems(ADateResults1);
                    }
                    break;

                case "NIC":
                    // Search by NIC
                    ObservableList<PartiesAndOfficers> nicResults = Model.getInstance().getDatabaseDriver().searchByNic(searchText);
                    partiesTable.setItems(nicResults);
                    break;

                case "Location":
                    // Search by Location
                    ObservableList<Accident> locationResults = Model.getInstance().getDatabaseDriver().searchByLocation(searchText);
                    accidenTable.setItems(locationResults);
                    break;

                case "Case Status":
                    // Search by Case Status
                    ObservableList<Accident> statusResults = Model.getInstance().getDatabaseDriver().searchByCaseStatus(searchText);
                    accidenTable.setItems(statusResults);
                    break;

                case "Officer Badge Number":
                    // Search by Officer Badge Number
                    ObservableList<PartiesAndOfficers> badgeResults = Model.getInstance().getDatabaseDriver().searchByBadgeNumber(searchText);
                    partiesTable.setItems(badgeResults);
                    break;

                default:
                    // If none of the cases match, show an error
                    ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Invalid search type selected.");
                    break;
            }
        } catch (Exception e) {
            // Handle any exceptions during the search process
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while performing the search.");
            e.printStackTrace();
        }
    }
}

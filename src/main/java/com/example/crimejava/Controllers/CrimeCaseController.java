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
import java.util.ResourceBundle;

public class CrimeCaseController implements Initializable {

    public TextField searchtxt;
    public Button searchbtn;
    public Button addcaseBtn;
    public Button refreshBtn;
    public ComboBox combobox;

    public TableView<Case> caseTable;
    public TableColumn<Case, String> caseNoCol;
    public TableColumn<Case, String> caseTypeCol;
    public TableColumn<Case, String> subTypeCol;
    public TableColumn<Case, String> stageofCase;
    public TableColumn<Case, String> stageCol;
    public TableColumn<Case, String> locationCol;
    public TableColumn<Case, String> filingNumCol;
    public TableColumn<Case, String> firstHearingDateCol;
    public TableColumn<Case, String> filingDtaeCol;
    public TableColumn<Case, String> discriptionCol;
    public TableColumn<Case, String> caseStautsCol;

    public TableView<Officer> officerTable;
    public TableColumn<Officer, String> officercaseNoCol;
    public TableColumn<Officer, String> policeStationCol;
    public TableColumn<Officer, String> criminalNameCol1;
    public TableColumn<Officer, String> criminalNicCol;
    public TableColumn<Officer, String> crimeDateCol;
    public TableColumn<Officer, String> officernameCol;
    public TableColumn<Officer, String> OfficerBadgeNumberCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        combobox.getItems().addAll(
                "Case ID",
                "Criminal Name",
                "Criminal Nic",
                "Crime Date",
                "Location",
                "Officer Badge Number",
                "Case Status"
        );

        // Setting up caseTable columns
        // Setting up caseTable columns
        caseNoCol.setCellValueFactory(new PropertyValueFactory<>("caseNo"));
        caseTypeCol.setCellValueFactory(new PropertyValueFactory<>("caseType"));
        subTypeCol.setCellValueFactory(new PropertyValueFactory<>("caseSubType"));
        stageofCase.setCellValueFactory(new PropertyValueFactory<>("stageOfCase"));
        stageCol.setCellValueFactory(new PropertyValueFactory<>("stage"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        filingNumCol.setCellValueFactory(new PropertyValueFactory<>("filingNumber"));
        filingDtaeCol.setCellValueFactory(new PropertyValueFactory<>("filingDate"));
        firstHearingDateCol.setCellValueFactory(new PropertyValueFactory<>("firstHearingDate"));
        discriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        caseStautsCol.setCellValueFactory(new PropertyValueFactory<>("caseStatus"));

        // Setting up officerTable columns
        officercaseNoCol.setCellValueFactory(new PropertyValueFactory<>("officerCaseNo"));
        policeStationCol.setCellValueFactory(new PropertyValueFactory<>("policeStation"));
        criminalNameCol1.setCellValueFactory(new PropertyValueFactory<>("criminalName"));
        criminalNicCol.setCellValueFactory(new PropertyValueFactory<>("criminalNic"));
        crimeDateCol.setCellValueFactory(new PropertyValueFactory<>("crimeDate"));
        officernameCol.setCellValueFactory(new PropertyValueFactory<>("officerName"));
        OfficerBadgeNumberCol.setCellValueFactory(new PropertyValueFactory<>("officerBadgeNumber"));

        // Add button action listeners
        addcaseBtn.setOnAction(actionEvent -> onCase());
        refreshBtn.setOnAction(actionEvent -> onRefresh());
        searchbtn.setOnAction(actionEvent -> onSearch());

        // Load data into the tables
        loadCaseTable();
        loadOfficerTable();
    }

    // Placeholder method to load data into caseTable
    private void loadCaseTable() {
        try {
            ObservableList<Case> AccidentData = Model.getInstance().getDatabaseDriver().getCaseDetails();
            caseTable.setItems(AccidentData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Placeholder method to load data into officerTable
    private void loadOfficerTable() {
        try {
            ObservableList<Officer> AccidentData = Model.getInstance().getDatabaseDriver().getOfficerDetails();
            officerTable.setItems(AccidentData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Placeholder method for handling 'Add Case' button click
    private void onCase() {
        // Get the current BorderPane from the MenuFullController
        BorderPane menuParent = (BorderPane) addcaseBtn.getScene().getRoot();
        menuParent.setCenter(Model.getInstance().getViewFactory().getCrimeAddView());
    }

    // Placeholder method for handling 'Refresh' button click
    private void onRefresh() {
        // Load data into the tables
        loadCaseTable();
        loadOfficerTable();

        searchtxt.setText("");
        combobox.getSelectionModel().clearSelection();
    }

    // Placeholder method for handling 'Search' button click
    private void onSearch() {
        String searchText = searchtxt.getText().trim();  // Retrieve the search text
        String selectedType = (String) combobox.getValue();  // Retrieve the selected search type from the ComboBox

        // Show an error if search text or search type is not provided
        if (searchText.isEmpty() || selectedType == null) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Please enter search text and select a search type.");
            return;
        }

        try {
            switch (selectedType) {
                case "Case ID":
                    // Search by Accident ID
                    ObservableList<Case> searchResults = Model.getInstance().getDatabaseDriver().searchCaseByNo(searchText);
                    ObservableList<Officer> searchResults2 = Model.getInstance().getDatabaseDriver().searchOfficerByCaseNo(searchText);
                    caseTable.setItems(searchResults);
                    officerTable.setItems(searchResults2);
                    break;

                case "Crime Date":
                    if (DateFormat.isValidDate(searchText)) {
                        ObservableList<Officer> searchResultsDob = Model.getInstance().getDatabaseDriver().searchOfficerByCrimeDate(searchText);
                        officerTable.setItems(searchResultsDob);
                    } else {
                        ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use yyyy-MM-dd or yyyy/MM/dd.");
                    }
                    break;

                case "Criminal Nic":
                    // Search by NIC
                    ObservableList<Officer> nicResults = Model.getInstance().getDatabaseDriver().searchCriminalByNic(searchText);
                    officerTable.setItems(nicResults);
                    break;

                case "Location":
                    // Search by Location
                    ObservableList<Case> locationResults = Model.getInstance().getDatabaseDriver().searchCaseByLocation(searchText);
                    caseTable.setItems(locationResults);
                    break;

                case "Officer Badge Number":
                    // Search by Case Status
                    ObservableList<Officer> statusResults = Model.getInstance().getDatabaseDriver().searchOfficerByBadgeNumber(searchText);
                    officerTable.setItems(statusResults);
                    break;

                case "Criminal Name":
                    ObservableList<Officer> NameResults = Model.getInstance().getDatabaseDriver().searchCriminalByName(searchText);
                    officerTable.setItems(NameResults);
                    break;

                case "Case Status":
                    // Search by Officer Badge Number
                    ObservableList<Case> badgeResults = Model.getInstance().getDatabaseDriver().searchCaseByCaseStatus(searchText);
                    caseTable.setItems(badgeResults);
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


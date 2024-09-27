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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ComplaintController implements Initializable {


    public TableView<Compalints> CompalintTable;
    public TableColumn<Compalints, String> complaintIDcol;
    public TableColumn<Compalints, String> fullNameCol;
    public TableColumn<Compalints, String> addresscol;
    public TableColumn<Compalints, String> citycol;
    public TableColumn<Compalints, String> numbercol;
    public TableColumn<Compalints, String> dobcol;
    public TableColumn<Compalints, Integer> agecol;
    public TableColumn<Compalints, String> nicCol;
    public TableColumn<Compalints, String> genderCol;
    public TableColumn<Compalints, String> emailCol;

    public TableView<Incident> IncidentTable;
    public TableColumn<Incident, String> complaintID2col;
    public TableColumn<Incident, String> incidentNatureCol;
    public TableColumn<Incident, String> DateCol;
    public TableColumn<Incident, String> howReportedCol;
    public TableColumn<Incident, String> locationCol;
    public TableColumn<Incident, String> DrscricCol;
    public TableColumn<Incident, String> DescriptionCol;
    public TextField searchtxt;
    public Button searchbtn;
    public ComboBox<String> combobox;
    public Button refreshBtn;
    public Button addCompalinsBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addCompalinsBtn.setOnAction(actionEvent -> onCompalins());
        refreshBtn.setOnAction(actionEvent -> onRefresh());
        searchbtn.setOnAction(actionEvent -> onSearch());

        combobox.getItems().addAll(
                "Complaint ID",
                "Date of Birth",
                "NIC",
                "Contact Number",
                "Incident Location"
        );

        // Setting up Complaints Table
        complaintIDcol.setCellValueFactory(new PropertyValueFactory<>("complaint_id"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        citycol.setCellValueFactory(new PropertyValueFactory<>("city_state_zip"));
        numbercol.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("age"));
        nicCol.setCellValueFactory(new PropertyValueFactory<>("nic"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Setting up Incident Table
        complaintID2col.setCellValueFactory(new PropertyValueFactory<>("complaint_id2"));
        incidentNatureCol.setCellValueFactory(new PropertyValueFactory<>("incident_nature"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("incident_date"));
        howReportedCol.setCellValueFactory(new PropertyValueFactory<>("how_reported"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location_of_incident"));
        DrscricCol.setCellValueFactory(new PropertyValueFactory<>("district_area"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Load data into the tables
        loadCompalintTable();
        loadIncidentTable();
    }

    private void loadCompalintTable() {
        try {
            ObservableList<Compalints> CompalintData = Model.getInstance().getDatabaseDriver().getComplaintDetails();
            CompalintTable.setItems(CompalintData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadIncidentTable() {
        try {
            ObservableList<Incident> IncidentData = Model.getInstance().getDatabaseDriver().getIncidentDetails();
            IncidentTable.setItems(IncidentData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCompalins() {
        BorderPane menuParent = (BorderPane) addCompalinsBtn.getScene().getRoot();
        menuParent.setCenter(Model.getInstance().getViewFactory().getAddCompalinsView());
    }

    public void onRefresh() {
        loadCompalintTable();
        loadIncidentTable();

        searchtxt.setText("");
        combobox.getSelectionModel().clearSelection();
    }

    public void onSearch() {
        String searchText = searchtxt.getText().trim();
        String selectedType = combobox.getValue();

        if (searchText.isEmpty() || selectedType == null) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Please enter search text and select a search type.");
            return;
        }

        try {
            switch (selectedType) {
                case "Complaint ID":
                    ObservableList<Compalints> searchResults = Model.getInstance().getDatabaseDriver().searchCompalintID(searchText);
                    ObservableList<Incident> searchResults2 = Model.getInstance().getDatabaseDriver().searchByID2(searchText);
                    CompalintTable.setItems(searchResults);
                    IncidentTable.setItems(searchResults2);
                    break;

                case "Date of Birth":
                    if (DateFormat.isValidDate(searchText)) {
                        ObservableList<Compalints> searchResultsDob = Model.getInstance().getDatabaseDriver().searchByDob(searchText);
                        CompalintTable.setItems(searchResultsDob);
                    } else {
                        ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Invalid date format. Please use yyyy-MM-dd or yyyy/MM/dd.");
                    }
                    break;

                case "NIC":
                    ObservableList<Compalints> searchResultsNic = Model.getInstance().getDatabaseDriver().searchByNIC(searchText);
                    CompalintTable.setItems(searchResultsNic);
                    break;

                case "Contact Number":
                    ObservableList<Compalints> searchResultsPhone = Model.getInstance().getDatabaseDriver().searchByPhoneNumber(searchText);
                    CompalintTable.setItems(searchResultsPhone);
                    break;

                case "Incident Location":
                    ObservableList<Incident> searchResultsLocation = Model.getInstance().getDatabaseDriver().searchByIncidentLocation(searchText);
                    IncidentTable.setItems(searchResultsLocation);
                    break;

                default:
                    ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Invalid search type selected.");
                    break;
            }
        } catch (SQLException e) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while performing the search.");
            e.printStackTrace();
        }
    }
}
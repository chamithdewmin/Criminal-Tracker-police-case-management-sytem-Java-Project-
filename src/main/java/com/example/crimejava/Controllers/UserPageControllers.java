package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Case;
import com.example.crimejava.Models.Model;
import com.example.crimejava.Models.Officer;
import com.example.crimejava.Models.Register;
import com.example.crimejava.Utils.DateFormat;
import com.example.crimejava.Utils.EmailValidator;
import com.example.crimejava.Utils.ShowAlert;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.crimejava.Utils.ShowAlert.showAlert;

public class UserPageControllers implements Initializable {

    public TextField searchtxt;
    public Button searchbtn;
    public ComboBox combobox;
    @FXML
    private TableView<Register> registerTable;
    @FXML
    private TableColumn<Register, Integer> idCol;
    @FXML
    private TableColumn<Register, String> genderCol;
    @FXML
    private TableColumn<Register, String> firstNameCol;
    @FXML
    private TableColumn<Register, String> lastNameCol;
    @FXML
    private TableColumn<Register, String> badgenumberCol;
    @FXML
    private TableColumn<Register, String> emailCol;

    @FXML
    private TextField idtxt;
    @FXML
    private TextField firstNametxt;
    @FXML
    private TextField lastNametxt;
    @FXML
    private TextField badgeNumbertxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private Button DeleteBtn;
    @FXML
    public Button UpdateBtn;
    @FXML
    public Button refreshBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        badgenumberCol.setCellValueFactory(new PropertyValueFactory<>("badgeNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadRegisterTable();
        DeleteBtn.setOnAction(actionEvent -> onDelete());
        UpdateBtn.setOnAction(actionEvent -> OnUpdate());
        refreshBtn.setOnAction(actionEvent -> onRefresh());
        searchbtn.setOnAction(actionEvent -> onSearch());

        combobox.getItems().addAll(
                "ID",
                "Officer Badge Number"
        );
    }

    private void loadRegisterTable() {
        try {
            ObservableList<Register> registerData = Model.getInstance().getDatabaseDriver().getRegisterDetails();
            registerTable.setItems(registerData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateFields() {
        if (idtxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please Enter user ID to update.");
            return false;
        }
        if (firstNametxt.getText().isEmpty() || lastNametxt.getText().isEmpty() || badgeNumbertxt.getText().isEmpty() || emailtxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all the fields to update.");
            return false;
        }

        if (!EmailValidator.isValidEmail(emailtxt.getText())) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please Enter a valid email.");
            return false;
        }
        return true;
    }

    private void onDelete() {
        if (idtxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Failure", "Please Enter user ID to delete.");
            return;
        }
        String id = idtxt.getText();

        boolean deleteData = Model.getInstance().getDatabaseDriver().deleteUserDataById(id);

        if (deleteData) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "User details deleted successfully!");
            loadRegisterTable();

            idtxt.setText("");
            firstNametxt.setText("");
            lastNametxt.setText("");
            badgeNumbertxt.setText("");
            emailtxt.setText("");

        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete user details.");
        }
    }

    private void OnUpdate() {
        if (!validateFields()) {
            return;
        }
        String id = idtxt.getText();
        String fname = firstNametxt.getText();
        String lname = lastNametxt.getText();
        String badgeNumber = badgeNumbertxt.getText();
        String email = emailtxt.getText();

        boolean updateData = Model.getInstance().getDatabaseDriver().updateUserDataFromAdminById(id, fname, lname, badgeNumber, email);

        if (updateData) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "User details updated successfully!");
            loadRegisterTable();

            idtxt.setText("");
            firstNametxt.setText("");
            lastNametxt.setText("");
            badgeNumbertxt.setText("");
            emailtxt.setText("");

        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update user details.");
        }
    }

    private void onRefresh() {

        loadRegisterTable();
        searchtxt.setText("");
        combobox.getSelectionModel().clearSelection();

    }

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
                case "ID":
                    // Check if the input for ID is numeric
                    if (!searchText.matches("\\d+")) {
                        ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid numeric ID.");
                        return;
                    }
                    // Search by ID
                    ObservableList<Register> IdResults = Model.getInstance().getDatabaseDriver().searchByRegisterId(searchText);
                    registerTable.setItems(IdResults);
                    break;

                case "Officer Badge Number":
                    // Search by Officer Badge Number
                    ObservableList<Register> BadgeNumberResults = Model.getInstance().getDatabaseDriver().searchByRegisterBadgeNumber(searchText);
                    registerTable.setItems(BadgeNumberResults);
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
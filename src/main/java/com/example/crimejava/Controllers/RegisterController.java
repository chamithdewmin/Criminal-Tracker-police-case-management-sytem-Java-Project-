package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import com.example.crimejava.Utils.EmailValidator;
import com.example.crimejava.Utils.ShowAlert;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.crimejava.Utils.ShowAlert.showAlert;

public class RegisterController implements Initializable {
    public TextField fname;
    public TextField lname;
    public TextField email;
    public TextField username;
    public CheckBox show_password;
    public PasswordField password;
    public Button createbutton;
    public Button check_username;
    public TextField passwordtxt;
    public TextField badgeNumber;
    public Label setFname;
    public Label setLname;
    public Label setBadgenumber;
    public Label setEmail;
    public Label setusername;
    public PasswordField setPassword;
    public ComboBox gendercombo;
    public ImageView girlIcon;
    public ImageView boyIcon;
    public ImageView NoneIcon;
    public Button refreshBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createbutton.setOnAction(actionEvent -> onCreate());
        check_username.setOnAction(actionEvent -> onCheck());
        show_password.setOnAction(actionEvent -> onShowPassword());
        gendercombo.setOnAction(actionEvent -> onIconChange());
        refreshBtn.setOnAction(actionEvent -> onUpdate());
        passwordtxt.setVisible(false);

        // Update text dynamically in labels
        setupDynamicTextUpdates();

        gendercombo.getItems().addAll("Sir", "Madam");
        NoneIcon.setVisible(true);
        girlIcon.setVisible(false);
        boyIcon.setVisible(false);
    }

    private void setupDynamicTextUpdates() {
        fname.setOnKeyTyped(event -> setFname.setText(" " + fname.getText()));
        lname.setOnKeyTyped(event -> setLname.setText(" " + lname.getText()));
        badgeNumber.setOnKeyTyped(event -> setBadgenumber.setText(" " + badgeNumber.getText()));
        email.setOnKeyTyped(event -> setEmail.setText(" " + email.getText()));
        username.setOnKeyTyped(event -> setusername.setText(" " + username.getText()));
        password.setOnKeyTyped(event -> setPassword.setText(password.getText()));
        passwordtxt.setOnKeyTyped(event -> setPassword.setText(passwordtxt.getText()));
    }

    public void onCreate() {
        if (isInputValid()) {
            String usernameToCheck = username.getText().trim();
            boolean usernameExists = Model.getInstance().getDatabaseDriver().isUsernameExists(usernameToCheck);

            if (usernameExists) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, "Username Taken", "The username is already in use.");
                return;
            }

            String hashedPassword = Model.getInstance().hashText(password.getText());
            String selectedGender = (String) gendercombo.getValue();

            boolean success = Model.getInstance().getDatabaseDriver().insertData(
                    fname.getText(),
                    lname.getText(),
                    email.getText(),
                    selectedGender,
                    badgeNumber.getText(),
                    username.getText(),
                    hashedPassword
            );

            if (success) {
                clearFields();
                ShowAlert.showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully");
            } else {
                ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "Account creation failed. Please try again.");
            }
        }
    }

    private boolean isInputValid() {
        if (fname.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "Please Enter your first name.");
            return false;
        }
        if (lname.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "Please Enter your last name.");
            return false;
        }
        if (email.getText().trim().isEmpty() || !EmailValidator.isValidEmail(email.getText())) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "Please Enter a valid email.");
            return false;
        }
        if (username.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "Please Enter a username.");
            return false;
        }
        if (password.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "Please Enter a password.");
            return false;
        }
        if (gendercombo.getValue() == null) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Please Please select a gender.");
            return false;
        }
        return true;
    }

    private void clearFields() {
        fname.clear();
        lname.clear();
        email.clear();
        badgeNumber.clear();
        username.clear();
        password.clear();
        passwordtxt.clear();
        gendercombo.getSelectionModel().clearSelection();
        gendercombo.setPromptText("Sir/Madam");
        setFname.setText("");
        setLname.setText("");
        setBadgenumber.setText("");
        setEmail.setText("");
        setusername.setText("");
        setPassword.setText("");
        NoneIcon.setVisible(true);
        girlIcon.setVisible(false);
        boyIcon.setVisible(false);
    }

    public void onCheck() {
        String usernameToCheck = username.getText().trim();
        if (usernameToCheck.isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please Enter a username to check.");
            return;
        }

        boolean exists = Model.getInstance().getDatabaseDriver().isUsernameExists(usernameToCheck);
        if (exists) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Username Taken", "The username is already in use.");
        } else {
            ShowAlert.showAlert(Alert.AlertType.INFORMATION, "Username Available", "The username is available.");
        }
    }

    private void onShowPassword() {
        if (show_password.isSelected()) {
            passwordtxt.setText(password.getText());
            passwordtxt.setVisible(true);
            password.setVisible(false);
        } else {
            password.setText(passwordtxt.getText());
            passwordtxt.setVisible(false);
            password.setVisible(true);
        }
    }

    private void onIconChange() {
        if (gendercombo.getValue() != null) {
            try {
                switch (gendercombo.getValue().toString()) {
                    case "Sir":
                        boyIcon.setVisible(true);
                        NoneIcon.setVisible(false);
                        girlIcon.setVisible(false);

                        break;

                    case "Madam":
                        girlIcon.setVisible(true);
                        boyIcon.setVisible(false);
                        NoneIcon.setVisible(false);
                        break;

                    default:
                        ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Invalid search type selected.");
                        break;
                }
            } catch (Exception e) {

            }
        }
    }

    private boolean updatevalidation() {
        if (username.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Error", "Please Enter your username to update.");
            return false; // Validation failed
        }
        return true; // Validation passed
    }


    private boolean updatevalidateFields() {
        if (username.getText().isEmpty() || fname.getText().isEmpty() || lname.getText().isEmpty() ||
                badgeNumber.getText().isEmpty() || email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all the fields to update");
            return false;
        }

        if (!EmailValidator.isValidEmail(email.getText())) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "Please Enter a valid email.");
            return false;
        }
        return true;
    }


    private void onUpdate() {
        if (updatevalidation() && onCheckUsername() && updatevalidateFields()) {
            String firstName = fname.getText().trim();
            String lastName = lname.getText().trim();
            String userEmail = email.getText().trim();
            String badgeNumberText = badgeNumber.getText().trim();
            String selectedGender = (String) gendercombo.getValue();
            String hashedPassword = Model.getInstance().hashText(password.getText().trim());

            boolean updateData = Model.getInstance().getDatabaseDriver()
                    .updateUserDataFromUser(username.getText().trim(), userEmail, firstName, lastName, badgeNumberText, hashedPassword, selectedGender);

            if (updateData) {
                clearFields();
                ShowAlert.showAlert(Alert.AlertType.INFORMATION, "Update Success", "User data updated successfully.");
            } else {
                ShowAlert.showAlert(Alert.AlertType.ERROR, "Update Failure", "Failed to update user data.");
            }
        }
    }

    public boolean onCheckUsername() {
        String usernameToCheck = username.getText().trim();

        boolean exists = Model.getInstance().getDatabaseDriver().isUsernameExists(usernameToCheck);
        if (exists) {
            ShowAlert.showAlert(Alert.AlertType.INFORMATION, "Username Exists", "This username can be used. Now enter the details you want to update.");
            return true;  // Username exists, continue with the update
        } else {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Username Not Found", "This username doesn't exist.");
            return false; // Username doesn't exist, update cannot proceed
        }
    }
}

package com.example.crimejava.Controllers;

import com.example.crimejava.Models.DatabaseDriver;
import com.example.crimejava.Models.Model;
import com.example.crimejava.Utils.ShowAlert;
import com.example.crimejava.Views.ViewFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public ImageView LoginIcon;
    public TextField usernametxt;
    public Label fogetpassword;
    public Button loginbtn;
    public CheckBox chk_password;
    public PasswordField password_fld;
    public TextField pasword_txt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize ViewFactory and DatabaseDriver

        loginbtn.setOnAction(actionEvent -> onLogin());
        fogetpassword.setOnMouseClicked(event -> ForgetPassword());
        chk_password.setOnAction(actionEvent -> onCheck());
    }

    private void onLogin() {
        String username = usernametxt.getText();
        String password = chk_password.isSelected() ? pasword_txt.getText() : password_fld.getText();

        // Evaluate credentials for user and admin
        Model.getInstance().evaluateUserCred(username, password);
        Model.getInstance().evaluateAdminCred(username, password);

        if (Model.getInstance().getUserLoginSuccessFlag() || Model.getInstance().getAdminLoginSuccessFlag()) {
            // Close the current login window
            Stage currentStage = (Stage) loginbtn.getScene().getWindow();
            if (currentStage != null) {
                currentStage.close();
            }
            // Show appropriate window depending on role
            if (Model.getInstance().getUserLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().ShowUserWindow();
            }
            if (Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().ShowAdminWindow();
            }
        } else {
            // Clear input fields and show an error alert if login fails
            clearFields();
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private void clearFields() {
        // Clear all input fields
        usernametxt.setText("");
        pasword_txt.setText("");
        password_fld.setText("");
    }


    private void ForgetPassword() {
        try {
            // Close the current stage and open the Forget Password window
            Stage currentStage = (Stage) fogetpassword.getScene().getWindow();
            if (currentStage != null) {
                currentStage.close();
            }

          Model.getInstance().getViewFactory().showForgetPassword();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onCheck() {
        if (chk_password.isSelected()) {
            // Checkbox is selected, show the password in the TextField
            pasword_txt.setText(password_fld.getText());
            pasword_txt.setVisible(true);
            password_fld.setVisible(false);
        } else {
            // Checkbox is deselected, hide the password and use the PasswordField
            password_fld.setText(pasword_txt.getText());
            pasword_txt.setVisible(false);
            password_fld.setVisible(true);
        }
    }
}


package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import com.example.crimejava.Utils.EmailValidator;
import com.example.crimejava.Utils.Notification;
import com.example.crimejava.Utils.Notification;
import com.example.crimejava.Utils.ShowAlert;
import com.example.crimejava.Views.ViewFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.crimejava.Utils.ShowAlert.showAlert;

public class ForgetPasswordController implements Initializable {

    public ImageView forget_icon;
    public TextField emailField;
    public Button sendButton;
    public TextField optField;
    public TextField newpField;
    public TextField compField;
    public Button resetButton;
    public Label forgetMassage;
    public Button backButton;
    public Button verifyOptButton;

    private ViewFactory viewFactory;

    private String verificationCode;
    private final SecureRandom random = new SecureRandom();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        resetButton.setDisable(true);
        viewFactory = Model.getInstance().getViewFactory();
        backButton.setOnAction(actionEvent -> BackLogin());
        sendButton.setOnAction(actionEvent -> onSend());
        verifyOptButton.setOnAction(actionEvent -> onOpt());
        resetButton.setOnAction(actionEvent -> onResetPassword());

    }

    public void BackLogin() {
        try {
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            if (currentStage != null) {
                currentStage.close();
            }
            viewFactory.showLoginWindow();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSend() {
        if (EmailValidations()) {

            String EmailToCheck = emailField.getText().trim();
            boolean exists = Model.getInstance().getDatabaseDriver().isEmailExists(EmailToCheck);

            if (exists) {
                String email = emailField.getText();

                // Generate a random integer between 0 and 999999
                int code = random.nextInt(999999);

                // Format the integer to ensure it's always 6 digits
                verificationCode = String.format("%06d", code);
                Notification.sendVerification(email,verificationCode);
                ShowAlert.showAlert(Alert.AlertType.INFORMATION, "Success", "OPT sent to: " + email);
            }else{
                showAlert(Alert.AlertType.ERROR, "Email Not Found", "The email address you entered does not match our records. Please check and try again.");
            }

        }
    }

        // All Validations

    private boolean EmailValidations(){

        if (emailField.getText() == null || emailField.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Email Required", "Please enter your email address.");
            return false;
        }
        if(!EmailValidator.isValidEmail(emailField.getText())) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Invalid Email Format", "The email address you entered is not valid. Please enter a valid email address.");
            return false;
        }
        return true;
    }

    private boolean OPTValidations() {
        if(optField.getText() == null || optField.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR,"OTP Required","Please enter the OTP sent to your email.");
            return false;
        }
        return true;
    }

    private boolean PasswordValidations() {
        if(newpField.getText() == null || newpField.getText().trim().isEmpty()){
            ShowAlert.showAlert(Alert.AlertType.ERROR, "New Password Required", "Please enter a new password.");
            return false;
        }
        if(compField.getText() == null || compField.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Confirm Password Required", "Please confirm your new password.");
            return false;
        }
        return true;
    }

    public void onOpt() {
        if(OPTValidations()){
            String opt = optField.getText();
            if(Objects.equals(opt, verificationCode)){
                ShowAlert.showAlert(Alert.AlertType.INFORMATION,"OTP Verified","OTP Verified. You can now proceed to reset your password.");
                resetButton.setDisable(false);
            }else{
                ShowAlert.showAlert(Alert.AlertType.ERROR,"Incorrect OTP","The OTP you entered is incorrect. Please try again.");
            }
        }
    }

    public void onResetPassword(){
        if(PasswordValidations()){
            String opt = optField.getText();
            String newPassword = newpField.getText();
            String confirmPassword = compField.getText();

            // Check if the OTP matches
            if(Objects.equals(opt, verificationCode)) {
                // Check if the new password and confirm password match
                if(newPassword.equals(confirmPassword)){
                    // Hash the new password
                    String hashedPassword = Model.getInstance().hashText(newPassword);
                    // Update the password in the database with the hashed password
                    boolean success = Model.getInstance().getDatabaseDriver().restPassword(emailField.getText(), hashedPassword);

                    if(success) {
                        ShowAlert.showAlert(Alert.AlertType.INFORMATION,"Password Reset Successful","Your password has been successfully reset.");

                        emailField.setText("");
                        optField.setText("");
                        newpField.setText("");
                        compField.setText("");
                        try {
                            Stage currentStage = (Stage) backButton.getScene().getWindow();
                            if (currentStage != null) {
                                currentStage.close();
                            }
                            viewFactory.showLoginWindow();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else{
                        ShowAlert.showAlert(Alert.AlertType.ERROR,"Password Reset Failed","There was an error resetting your password. Please try again later.");
                    }
                }else {
                    ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "The new password and confirmation password do not match. Please try again.");
                }
            }else{
                ShowAlert.showAlert(Alert.AlertType.ERROR, "Failure", "OTP does not match!");
            }
        }
    }
}

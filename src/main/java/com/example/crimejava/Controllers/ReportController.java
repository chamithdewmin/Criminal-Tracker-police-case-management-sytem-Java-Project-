package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import com.example.crimejava.Utils.EmailValidator;
import com.example.crimejava.Utils.Notification;
import com.example.crimejava.Utils.ShowAlert;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.example.crimejava.Utils.ShowAlert.showAlert;

public class ReportController implements Initializable {

    public TextField BugTitleTxt;
    public TextArea DescriptionTxt;
    public Button SubmitBtn;
    public Button backBtn;
    public TextField Emailtxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SubmitBtn.setOnAction(actionEvent -> onSubmit());
        backBtn.setOnAction(actionEvent -> onbackDashboard());
    }

    private void onSubmit() {
        if (Validations()) {
            String bugTitle = BugTitleTxt.getText().trim();
            String description = DescriptionTxt.getText().trim();
            String userEmail = Emailtxt.getText().trim();
            String currentDate = LocalDate.now().toString();

            try {
                Notification.sendBugReport("chamithsamarakon@gmail.com", userEmail, bugTitle, description, currentDate);
                Notification.sendUserFeedbackEmail(userEmail, currentDate);

                ShowAlert.showAlert(Alert.AlertType.INFORMATION, "Success", "Your bug report has been submitted successfully. Thank you for your valuable feedback!");

                BugTitleTxt.setText("");
                DescriptionTxt.setText("");
                Emailtxt.setText("");

                onbackDashboard();

            } catch (Exception e) {
                ShowAlert.showAlert(Alert.AlertType.ERROR, "Submission Failed", "An error occurred while submitting the bug report. Please try again later.");
            }

        }
    }

    private void onbackDashboard() {
        try {
            Stage currentStage = (Stage) backBtn.getScene().getWindow();
            if (currentStage != null) {
                currentStage.close();
                if (currentStage.getTitle().equals("User Window")) {
                    Model.getInstance().getViewFactory().ShowUserWindow();
                } else {
                    Model.getInstance().getViewFactory().ShowAdminWindow();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Navigation Error", "An error occurred while navigating to the dashboard. Please try again.");
        }
    }

    private boolean Validations() {
        if (Emailtxt.getText() == null || Emailtxt.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Email Required", "Your email address is required.");
            return false;
        }
        if (!EmailValidator.isValidEmail(Emailtxt.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Enter a valid email.");
            return false;
        }
        if (BugTitleTxt.getText() == null || BugTitleTxt.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Title Required", "Title of bug is required.");
            return false;
        }
        if (DescriptionTxt.getText() == null || DescriptionTxt.getText().trim().isEmpty()) {
            ShowAlert.showAlert(Alert.AlertType.ERROR, "Description Required", "Description is required.");
            return false;
        }
        return true;
    }
}

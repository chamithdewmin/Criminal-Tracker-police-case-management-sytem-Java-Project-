package com.example.crimejava.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class ShowAlert {

    public static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Apply custom CSS for black background
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(ShowAlert.class.getResource("/styles/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");

        // Adjusting size
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}

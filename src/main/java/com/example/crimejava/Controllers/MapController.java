package com.example.crimejava.Controllers;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private Button refreshBtn;

    @FXML
    private WebView MapWebView;

    private WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = MapWebView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        // Load the HTML file
        URL resource = getClass().getResource("/HTML/Map.html");
        if (resource != null) {
            webEngine.load(resource.toExternalForm());
        } else {
            System.err.println("Resource not found: /HTML/Map.html");
        }

        // Set action for refresh button
        refreshBtn.setOnAction(actionEvent -> onRefresh());

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.FAILED) {
                System.err.println("Failed to load: " + webEngine.getLocation());
            }
        });
    }

    @FXML
    private void onRefresh() {
        // Reload the HTML file
        URL resource = getClass().getResource("/HTML/Map.html");
        if (resource != null) {
            webEngine.load(resource.toExternalForm());
        } else {
            System.err.println("Resource not found: /HTML/Map.html");
        }
    }
}

package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuFullController implements Initializable {

    public BorderPane menuParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Model.getInstance().getViewFactory().getMenuItem().addListener((obseravableValue, oldVal, newVal) -> {
            switch (newVal) {
                case CRIMECASE -> menuParent.setCenter(Model.getInstance().getViewFactory().ShowCrimeCaseView());
                case ACCIDENTREPORTS -> menuParent.setCenter(Model.getInstance().getViewFactory().ShowAccidentReportsView());
                case TRACKBYMAP -> menuParent.setCenter(Model.getInstance().getViewFactory().ShowMapWebView());
                case COMPLAINTS -> menuParent.setCenter(Model.getInstance().getViewFactory().ShowCompalinsView());
                case USER -> menuParent.setCenter(Model.getInstance().getViewFactory().showuserView());
                case REGISTER -> menuParent.setCenter(Model.getInstance().getViewFactory().ShowRegisterView());
                default -> menuParent.setCenter(Model.getInstance().getViewFactory().ShowDashboardView());
            }
        });

    }
}


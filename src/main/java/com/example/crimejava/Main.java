package com.example.crimejava;

import com.example.crimejava.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       Model.getInstance().getViewFactory().showLoginWindow();
    }
    public static void main(String[] args) {
        System.setProperty("prism.forceGPU", "true");
        launch(args);
    }
}


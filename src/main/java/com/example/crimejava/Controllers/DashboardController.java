package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import com.example.crimejava.Models.Case;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {

    public javafx.scene.chart.LineChart LineChart;
    public javafx.scene.chart.BarChart<String, Number> BarChart;
    public PieChart PieChart;
    public Label time;
    public DatePicker date;
    public Label NameLable;

    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BarChart();
        PieChart();
        setupClock();
        OpenLineChart();

        String username;
        if (Model.getInstance().isAdmin()) { // Assuming isAdmin() returns true if the user is an admin
            username = Model.getInstance().getAdminAccount().getAdminUsername();
        } else {
            username = Model.getInstance().getUserAccounts().getUserUsername();
        }

        NameLable.setText("Hi, " + username);
    }


        private void BarChart() {
        // Retrieve case details from database
        List<Case> caseList = Model.getInstance().getDatabaseDriver().getCaseDetails();

        // Process data into series
        XYChart.Series<String, Number> series2022 = new XYChart.Series<>();
        series2022.setName("2022");

        XYChart.Series<String, Number> series2023 = new XYChart.Series<>();
        series2023.setName("2023");

        XYChart.Series<String, Number> series2024 = new XYChart.Series<>();
        series2024.setName("2024");

        // Initialize the data
        Map<String, Map<Integer, Long>> casesByYearAndLocation = caseList.stream()
                .collect(Collectors.groupingBy(
                        Case::getLocation,
                        Collectors.groupingBy(c -> Integer.parseInt(c.getFilingDate().split("-")[0]), Collectors.counting())
                ));

        for (Map.Entry<String, Map<Integer, Long>> locationEntry : casesByYearAndLocation.entrySet()) {
            String location = locationEntry.getKey();
            Map<Integer, Long> yearData = locationEntry.getValue();

            series2022.getData().add(new XYChart.Data<>(location, yearData.getOrDefault(2022, 0L)));
            series2023.getData().add(new XYChart.Data<>(location, yearData.getOrDefault(2023, 0L)));
            series2024.getData().add(new XYChart.Data<>(location, yearData.getOrDefault(2024, 0L)));
        }

        BarChart.getData().clear();
        BarChart.getData().addAll(series2022, series2023, series2024);
    }


    private void OpenLineChart() {

        List<Case> caseList = Model.getInstance().getDatabaseDriver().getCaseDetails();

        XYChart.Series<String, Number> series2022 = new XYChart.Series<>();
        series2022.setName("2022");
        XYChart.Series<String, Number> series2023 = new XYChart.Series<>();
        series2023.setName("2023");

        XYChart.Series<String, Number> series2024 = new XYChart.Series<>();
        series2024.setName("2024");
        Map<String, Map<Integer, Long>> casesByYearAndLocation = caseList.stream()
                .collect(Collectors.groupingBy(
                        Case::getLocation,
                        Collectors.groupingBy(c -> Integer.parseInt(c.getFilingDate().split("-")[0]), Collectors.counting())
                ));

        for (Map.Entry<String, Map<Integer, Long>> locationEntry : casesByYearAndLocation.entrySet()) {
            String location = locationEntry.getKey();
            Map<Integer, Long> yearData = locationEntry.getValue();

            series2022.getData().add(new XYChart.Data<>(location, yearData.getOrDefault(2022, 0L)));
            series2023.getData().add(new XYChart.Data<>(location, yearData.getOrDefault(2023, 0L)));
            series2024.getData().add(new XYChart.Data<>(location, yearData.getOrDefault(2024, 0L)));

            LineChart.getData().clear();
            LineChart.getData().addAll(series2022, series2023, series2024);
        }

    }

    private void PieChart() {
        // Retrieve case details from database
        List<Case> caseList = Model.getInstance().getDatabaseDriver().getCaseDetails();

        // Initialize pie chart data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                caseList.stream()
                        .collect(Collectors.groupingBy(Case::getCaseType, Collectors.counting()))
                        .entrySet().stream()
                        .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList())
        );

        pieChartData.forEach(data -> data.nameProperty().bind(
                Bindings.concat(data.getName(), " ", data.pieValueProperty()))
        );

        PieChart.getData().clear();
        PieChart.getData().addAll(pieChartData);
    }


    private void setupClock() {
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                time.setText(sdf.format(new Date()));
            }), new KeyFrame(Duration.seconds(1)));
            clock.setCycleCount(Timeline.INDEFINITE);
            clock.play();
        }
    }
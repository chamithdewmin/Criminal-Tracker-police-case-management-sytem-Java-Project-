package com.example.crimejava.Views;


import com.example.crimejava.Controllers.UserMenuFullController;
import com.example.crimejava.Main;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ViewFactory {

    private final ObjectProperty<MenuOption> MenuItem;
    private AnchorPane dashboardView;
    private AnchorPane crimeCaseView;
    private AnchorPane RegisterView;
    private AnchorPane crimeaddView;
    private AnchorPane AddAccident;
    private AnchorPane AddicentRe;
    private AnchorPane CompalinsView;
    private AnchorPane AddCompalinsView;
    private AnchorPane MapView;
    private AnchorPane UserView;


    public ViewFactory() {
        this.MenuItem = new SimpleObjectProperty<>();
    }

    public ObjectProperty<MenuOption> getMenuItem() {
        return MenuItem;
    }

    public AnchorPane ShowDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane ShowCrimeCaseView() {
        if (crimeCaseView == null) {
            try {
                crimeCaseView = new FXMLLoader(getClass().getResource("/Fxml/CrimeCase.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return crimeCaseView;
    }

    public AnchorPane getCrimeAddView() {
        if (crimeaddView == null) {
            try {
                crimeaddView = new FXMLLoader(getClass().getResource("/Fxml/CaseAdd.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return crimeaddView;
    }

    public AnchorPane ShowAccidentReportsView() {
        if (AddicentRe == null) {
            try {
                AddicentRe = new FXMLLoader(getClass().getResource("/Fxml/AccidentReports.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return AddicentRe;
    }

    public AnchorPane getAddAccidentReportsView() {
        if (AddAccident == null) {
            try {
                AddAccident = new FXMLLoader(getClass().getResource("/Fxml/AddAccidentReports.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return AddAccident;
    }

    public AnchorPane ShowRegisterView() {
        if (RegisterView == null) {
            try {
                RegisterView = new FXMLLoader(getClass().getResource("/Fxml/Register.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RegisterView;
    }

    public AnchorPane ShowCompalinsView() {
        if (CompalinsView == null) {
            try {
                CompalinsView = new FXMLLoader(getClass().getResource("/Fxml/Complains.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return CompalinsView;
    }

    public AnchorPane getAddCompalinsView() {
        if (AddCompalinsView == null) {
            try {
                AddCompalinsView = new FXMLLoader(getClass().getResource("/Fxml/AddComplains.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return AddCompalinsView;
    }

    public void showLoginWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Fxml/Login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Criminal Tracker");
        stage.show();
    }


    public void showForgetPassword() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/ForgetPassword.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Forget Password");
        stage.show();
    }

    public void ShowUserWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/UserMenuFull.fxml"));
        UserMenuFullController menuFullController = new UserMenuFullController();
        loader.setController(menuFullController);

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Criminal Tracker");
        stage.show();
    }


    public void ShowAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AdminMenuFull.fxml"));
        UserMenuFullController menuFullController = new UserMenuFullController();
        loader.setController(menuFullController);

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Criminal Tracker");
        stage.show();
    }

    public AnchorPane ShowMapWebView() {
        if (MapView == null) {
            try {
                MapView = new FXMLLoader(getClass().getResource("/Fxml/Map.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return MapView;
    }

    public void showReportView() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Fxml/ReportPage.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Criminal Tracker");
        stage.show();
    }

    public AnchorPane showuserView() {
        if (UserView == null) {
            try {
                UserView = new FXMLLoader(getClass().getResource("/Fxml/UserPage.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return UserView;
    }
}



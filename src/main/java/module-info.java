module com.example.crimejava {
    requires java.datatransfer;
    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jbcrypt;
    requires javafx.web;
    requires java.sdk; // If you're using an SDK, verify the correct name.

    // Opening the controllers package to allow JavaFX to access it.
    opens com.example.crimejava.Controllers to javafx.fxml;

    // You may need to open the Fxml and Model classes if they are used in FXML files
    opens com.example.crimejava.Models to javafx.fxml;
    opens com.example.crimejava.Views to javafx.fxml;

    // Exporting the packages
    exports com.example.crimejava;
    exports com.example.crimejava.Models;
    exports com.example.crimejava.Views;
    exports com.example.crimejava.Controllers;
    exports com.example.crimejava.Utils;
}

module com.example.resourcereservingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.resourcereservingsystem to javafx.fxml;
    exports com.example.resourcereservingsystem;
}
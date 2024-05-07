module Login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Login to javafx.fxml;
    exports Login;

}
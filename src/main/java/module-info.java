module java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Login to javafx.fxml;
    exports Login;
    opens OpenEvent to javafx.fxml;
    exports OpenEvent;
//    opens MainPanel to javafx.fxml;
//    exports MainPanel;


}


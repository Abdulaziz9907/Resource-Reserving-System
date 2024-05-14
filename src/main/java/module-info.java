module java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens Login to javafx.fxml;
    exports Login;

    opens OpenEvent to javafx.fxml;
    exports OpenEvent;

    opens MainPanel to javafx.fxml;
    exports MainPanel;

    opens ViewReservations to javafx.fxml;
    exports ViewReservations;

    opens ReserveLabsClasses to javafx.fxml;
    exports ReserveLabsClasses;

    opens ReserveFacilities to javafx.fxml;
    exports ReserveFacilities;

    opens ShowEvents to javafx.fxml;
    exports ShowEvents;



}


package MainPanel;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainPanel extends Application {
    //Creates the buttons
    @FXML
    private Button OpenEvent_button;
    @FXML
    private Button ViewReservation_button;
    @Override
    public void start(Stage primaryStage) throws Exception {

        OpenEvent_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Display OpenEvent.fxml
                    Parent root = FXMLLoader.load(getClass().getResource("OpenEvent.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }

                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ViewReservation_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Display ViewReservations.fxml
                    Parent root = FXMLLoader.load(getClass().getResource("ViewReservations.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

package MainPanel;

import Login.DB;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class MainPanel  {
    //Creates the buttons
    @FXML
    private Button OpenEvent_button;
    @FXML
    private Button ViewReservation_button;

    Parent root = null;



    public void changeScene(ActionEvent primaryStage) throws Exception {

        OpenEvent_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    root = FXMLLoader.load(MainPanel.class.getResource("OpenEvent.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }



        });

        ViewReservation_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    root = FXMLLoader.load(MainPanel.class.getResource("ViewReservations.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}

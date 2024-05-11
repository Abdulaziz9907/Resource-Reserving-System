package MainPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanel implements Initializable {
    //Creates the buttons
    @FXML
    private Button OpenEvent_button;
    @FXML
    private Button ViewReservation_button;
    @FXML
    private Button exit_button;
    @FXML
    private Button ReserveFacilities_Button;

    Parent root = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            OpenEvent_button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    try {
                        root = FXMLLoader.load(getClass().getResource("/OpenEvent.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("Open Event");
                        stage.setScene(new Scene(root, 600, 400));
                        stage.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


            });

            ViewReservation_button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        root = FXMLLoader.load(getClass().getResource("/ViewReservations.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle("View Reservation");
                        stage.setScene(new Scene(root, 600, 400));
                        stage.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

            exit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    root = FXMLLoader.load(getClass().getResource("/MainLogin.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Log in");
                    stage.setScene(new Scene(root, 600, 400));
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }


        });
        ReserveFacilities_Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    root = FXMLLoader.load(getClass().getResource("/ReserveFacilities.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Facility Reservation");
                    stage.setScene(new Scene(root, 600, 400));
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        });

    }

}

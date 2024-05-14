package MainPanel;
import Login.DB;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Button ReserveLabsClasses_button;
    @FXML
    private Button exit_button;
    @FXML
    private Button ReserveFacilities_Button;
    @FXML
    private Button ShowEvents_button;
    @FXML
    private Text name_text;




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
        ShowEvents_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    root = FXMLLoader.load(getClass().getResource("/ShowEvents.fxml"));
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

        ReserveLabsClasses_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    root = FXMLLoader.load(getClass().getResource("/ReserveLabsClasses.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Reserve Labs and Classes");
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



    public void userInfo(String username){

        name_text.setText(("Welcome "+username));

        name_text.setTranslateX(-300);

        TranslateTransition transition3 = new TranslateTransition(Duration.seconds(1), name_text);
        transition3.setToX(0);

        transition3.setInterpolator(Interpolator.SPLINE(0.25, 0.1, 0.25, 1));

        transition3.play();

    }

}

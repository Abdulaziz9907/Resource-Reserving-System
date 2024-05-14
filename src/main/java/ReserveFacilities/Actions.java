package ReserveFacilities;
import Login.DB;
import MainPanel.MainPanel;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.net.URL;
import java.util.ResourceBundle;
public class Actions implements Initializable {

    @FXML
    private Button Facility_Apply;
    @FXML
    private Button Facility_Return;
    @FXML
    private TextField Facility_Name;
    @FXML
    private DatePicker Facility_Date;
    @FXML
    private TextField Facility_Location;
    @FXML
    private TextField Facility_TimeS;
    @FXML
    private TextField Facility_TimeE;
    @FXML
    private Label confirmation_M;
    private String gender = DB.getGender();
    @FXML
    private TextArea Details_M;
    Parent root = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        Facility_Apply.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {

                LocalDate date = Facility_Date.getValue();

                if (!Facility_Name.getText().trim().isEmpty()  && !Facility_Location.getText().trim().isEmpty() && !Facility_TimeS.getText().trim().isEmpty() && !Facility_TimeS.getText().trim().isEmpty() && !Facility_TimeE.getText().trim().isEmpty() && !Details_M.getText().trim().isEmpty()) {
                    java.sql.Date sqlDate = Date.valueOf(date);
                    String details = Facility_Name.getText()+"-"+Facility_Name.getText()+"-"+Facility_Location.getText();

                    DataBase_ReserveFacilities.FaciltiesReservation(event,details, sqlDate, Facility_TimeS.getText(),Facility_TimeE.getText(), gender,Details_M.getText());
                    confirmation_M.setText("The facility has been assigned successfully");

                } else {
                    System.out.println("fill all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("fill all the information");
                    alert.show();
                }
            }


        });
        Facility_Return.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //root = FXMLLoader.load(getClass().getResource("/mainPanel.fxml"));

                    root = FXMLLoader.load(getClass().getResource("/mainPanel.fxml"));


                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Log in");
                    stage.setScene(new Scene(root, 600, 400));
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
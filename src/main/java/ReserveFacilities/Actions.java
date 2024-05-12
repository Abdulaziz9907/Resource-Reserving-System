package ReserveFacilities;
import Login.DB;
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
    private TextField Facility_Time;
    @FXML
    private RadioButton fMale_option;
    @FXML
    private RadioButton fFemale_option;
    @FXML
    private Label confirmation_M;
    Parent root = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        ToggleGroup toggleGroup = new ToggleGroup();
        fMale_option.setToggleGroup(toggleGroup);
        fFemale_option.setToggleGroup(toggleGroup);
        fMale_option.setSelected(true);

        String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

        Facility_Apply.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                LocalDate date = Facility_Date.getValue();

                if (!Facility_Name.getText().trim().isEmpty()  && !Facility_Location.getText().trim().isEmpty() && !Facility_Time.getText().trim().isEmpty()) {
                    java.sql.Date sqlDate = Date.valueOf(date);
                    DataBase_ReserveFacilities.FaciltiesReservation(event, Facility_Name.getText(), sqlDate, Facility_Location.getText(), Facility_Time.getText(), toggleName);
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
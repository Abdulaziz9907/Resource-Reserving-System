package ReserveFacilities;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.Date;
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
    private LocalDate Facility_Date;
    @FXML
    private TextField Facility_Location;
    @FXML
    private TextField Facility_Time;
    @FXML
    private RadioButton fMale_option;
    @FXML
    private RadioButton fFemale_option;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Facility_Apply.setOnAction(new EventHandler<ActionEvent>(){
            ToggleGroup toggleGroup=new ToggleGroup();
            fMale_option.setToggleGroup(toggleGroup);
            fFemale_option.setToggleGroup(toggleGroup);
            @Override
            public void handle(ActionEvent event){DataBase_ReserveFacilities.FaciltiesReservation(event,
                    Facility_Name.getText(),
                    Facility_Date.getValue(),
                    Facility_Location.getText(),
                    Facility_Time.getText(),);}
        }
        );}
}


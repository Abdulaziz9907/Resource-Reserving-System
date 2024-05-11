package ReserveFacilities;
import Login.DB;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private DatePicker Facility_Date;
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
        LocalDate date = Facility_Date.getValue();

        ToggleGroup toggleGroup = new ToggleGroup();
        fMale_option.setToggleGroup(toggleGroup);
        fFemale_option.setToggleGroup(toggleGroup);

        fMale_option.setSelected(true);

        String toggleName = ((RadioButton)toggleGroup.getSelectedToggle()).getText();
        Facility_Apply.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {(event, Facility_Name.getText(),
                        date,
                        Facility_Location.getText(),
                        Facility_Time.getText(),
                        toggleName);}
        });
        if(!Facility_Name.getText().trim().isEmpty() && !date.isEqual(null)&&!Facility_Location.getText().trim().isEmpty()&&!Facility_Time.getText().trim().isEmpty()){
            DataBase_ReserveFacilities.FaciltiesReservation(event, Facility_Name.getText(), date, Facility_Location.getText(), Facility_Time.getText(), toggleName);}
        }

        else{

            System.out.println("fill all information");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("fill all the information");
            alert.show();
        }

    }
}



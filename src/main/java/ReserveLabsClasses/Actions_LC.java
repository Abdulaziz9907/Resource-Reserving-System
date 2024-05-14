package ReserveLabsClasses;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class Actions_LC implements Initializable {

    @FXML
    private ChoiceBox<String> Choice_CL;
    @FXML
    private TextField bldgNum;
    @FXML
    private TextField roomNum;
    @FXML
    private  DatePicker lC_Date;
    @FXML
    private TextField lC_Time_Start;
    @FXML
    private TextField lC_Time_End;
    @FXML
    private RadioButton fMale_lC;
    @FXML
    private RadioButton fFemale_lC;
    @FXML
    private TextArea Details_lC;
    @FXML
    private Button Apply_lC;
    @FXML
    private Button Return_lC;
    @FXML
    private Label confirmationM;

    Parent root = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //ToggleGroup toggleGroup = new ToggleGroup();
        //fMale_lC.setToggleGroup(toggleGroup);
        //fFemale_lC.setToggleGroup(toggleGroup);
        //fMale_lC.setSelected(true);


        //String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

        // for the choice box
        ObservableList<String> choices = FXCollections.observableArrayList("Lab", "Class");
        Choice_CL.setItems(choices);
        Choice_CL.setValue("Lab");

        Apply_lC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate date = lC_Date.getValue();

                if (Choice_CL.getValue() != null && !lC_Time_Start.getText().trim().isEmpty() && !lC_Time_End.getText().trim().isEmpty() && !roomNum.getText().trim().isEmpty() && !bldgNum.getText().trim().isEmpty() ) {
                    java.sql.Date sqlDate = Date.valueOf(date);
                    String selectedChoice = (String) Choice_CL.getValue();
                    ReserveLabsClasses_DB.Reserve_CL(event, selectedChoice,roomNum.getText(),bldgNum.getText(), sqlDate, lC_Time_Start.getText(),lC_Time_End.getText(), Details_lC.getText());

                    confirmationM.setText("The facility has been assigned successfully");
                } else {
                    System.out.println("Fill all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all the information");
                    alert.show();
                }
            }
        });
        Return_lC.setOnAction(new EventHandler<ActionEvent>() {
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

package OpenEvent;

import Login.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class OpenEvent implements Initializable {
    @FXML
    private ChoiceBox<String> facilityChoices;
    @FXML
    private TextField nOfParticipants;
    @FXML
    private TextField eventStart;
    @FXML
    private DatePicker eventDate;
    @FXML
    private TextField eventEnd;
    private Stage stage;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/facilities",
                    "root",
                    "123123"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            if(DB.getGender().equals("male")) {
                resultSet = statement.executeQuery("SELECT name FROM facilities.facility_table");
            }
            else{resultSet = statement.executeQuery("SELECT name FROM facilities.female_facility_table");}

            while (resultSet.next()){
                facilityChoices.getItems().add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void handle(ActionEvent event){

        if(facilityChoices.getValue() != null && !nOfParticipants.getText().isEmpty() && eventDate.getValue() != null && !eventStart.getText().isEmpty() && !eventEnd.getText().isEmpty()){
            events_DB.openEvent(facilityChoices.getValue(),nOfParticipants.getText(),String.valueOf(eventDate.getValue()), eventStart.getText(), eventEnd.getText());
            System.out.println(events_DB.getIsBooked());
            if(events_DB.getIsBooked())
                return;

            Parent root = null;
            try {
                root = FXMLLoader.load(DB.class.getResource("/OpenEventConfirmed.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("fill all the information");
            alert.show();
        }


    }
    public void returnScene(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(DB.class.getResource("/mainPanel.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }



}

package ShowEvents;

import Login.DB;
import MainPanel.MainPanel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowEvents implements Initializable {

    @FXML
    TableColumn<Event,String> colID;
    @FXML
    TableColumn<Event,String> colFacility;
    @FXML
    TableColumn<Event,Integer> colParticipants;
    @FXML
    TableColumn<Event,String> colDate;
    @FXML
    TableColumn<Event,String> colStartTime;
    @FXML
    TableColumn<Event,String> colEndTime;
    @FXML
    TableView<Event> eventsTable;
    ObservableList<Event> eventsList;

    @FXML
    private TextField txtID;
    @FXML
    private TextField txtFacility;
    @FXML
    private TextField txtParticipants;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;
    private Stage stage;
    int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<Event,String>("ID"));
        colFacility.setCellValueFactory(new PropertyValueFactory<Event,String>("facility"));
        colParticipants.setCellValueFactory(new PropertyValueFactory<Event,Integer>("participants"));
        colDate.setCellValueFactory(new PropertyValueFactory<Event,String>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<Event,String>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<Event,String>("endTime"));

        eventsList = ShowEvents_DB.getEvents();
        eventsTable.setItems(eventsList);

    }
    public void getSelected(MouseEvent event){
        index = eventsTable.getSelectionModel().getSelectedIndex();
        if(index <= -1)
            return;
        txtID.setText(String.valueOf(colID.getCellData(index)));
        txtFacility.setText(colFacility.getCellData(index));
        txtParticipants.setText(String.valueOf(colParticipants.getCellData(index)));
        txtDate.setText(colDate.getCellData(index));
        txtStart.setText(colStartTime.getCellData(index));
        txtEnd.setText(colEndTime.getCellData(index));

    }
    public void register(ActionEvent event){
        if(!txtID.getText().isEmpty() &&!txtFacility.getText().isEmpty() &&!txtParticipants.getText().isEmpty() &&!txtDate.getText().isEmpty() &&!txtStart.getText().isEmpty() &&!txtEnd.getText().isEmpty())
            ShowEvents_DB.registerUser(event,txtID.getText(),txtFacility.getText(),txtParticipants.getText(),txtDate.getText(),txtStart.getText(),txtEnd.getText());
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all information");
            alert.show();
        }
    }
    public void cancel(ActionEvent event){
        if(!String.valueOf(DB.getRole()).equals("admin")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Only Admin is Allowed to Cancel");
            alert.show();
            return;
        }
        if(!txtID.getText().isEmpty() &&!txtFacility.getText().isEmpty() &&!txtParticipants.getText().isEmpty() &&!txtDate.getText().isEmpty() &&!txtStart.getText().isEmpty() &&!txtEnd.getText().isEmpty())
            ShowEvents_DB.cancelEvent(event, txtID.getText(),txtFacility.getText(),txtParticipants.getText(),txtDate.getText(),txtStart.getText(),txtEnd.getText());
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all information");
            alert.show();
        }
    }

    public void handle(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/mainPanel.fxml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }}

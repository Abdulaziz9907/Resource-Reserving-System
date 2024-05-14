package ViewReservations;

import Login.DB;
import MainPanel.MainPanel;
import ShowEvents.Event;
import ShowEvents.ShowEvents_DB;
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

public class ViewReservations implements Initializable {
    @FXML
    TableColumn<Reservation, Integer> colID;
    @FXML
    TableColumn<Reservation,String> colReservation;
    @FXML
    TableColumn<Reservation,String> colGender;
    @FXML
    TableColumn<Reservation,String> colDate;
    @FXML
    TableColumn<Reservation,String> colStartTime;
    @FXML
    TableColumn<Reservation,String> colEndTime;
    @FXML
    TableView<Reservation> reservationsTable;
    ObservableList<Reservation> reservationsList;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtReservation;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtgender;
    private Stage stage;
    int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("ID"));
        colReservation.setCellValueFactory(new PropertyValueFactory<Reservation,String>("reservation"));
        colGender.setCellValueFactory(new PropertyValueFactory<Reservation,String>("gender"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reservation,String>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<Reservation,String>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<Reservation,String>("endTime"));

        reservationsList = ViewReservations_DB.getReservations();
        reservationsTable.setItems(reservationsList);

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
    }
    public void getSelected(MouseEvent event){
        index = reservationsTable.getSelectionModel().getSelectedIndex();
        if(index <= -1)
            return;
        txtID.setText(String.valueOf(colID.getCellData(index)));
        txtReservation.setText(colReservation.getCellData(index));
        txtgender.setText(String.valueOf(colGender.getCellData(index)));
        txtDate.setText(colDate.getCellData(index));
        txtStart.setText(colStartTime.getCellData(index));
        txtEnd.setText(colEndTime.getCellData(index));

    }


    public void cancel(ActionEvent event){
        if(!txtID.getText().isEmpty() &&!txtReservation.getText().isEmpty() &&!txtgender.getText().isEmpty() &&!txtDate.getText().isEmpty() &&!txtStart.getText().isEmpty() &&!txtEnd.getText().isEmpty())
            ViewReservations_DB.cancelReservation(event,txtID.getText(),txtReservation.getText(),txtgender.getText(),txtDate.getText(),txtStart.getText(),txtEnd.getText());
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all information");
            alert.show();
        }
    }

}

package ViewReservations;

import Login.DB;
import OpenEvent.OpenEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class ViewReservations_DB {

    public static ObservableList<Reservation> getReservations(){
        String sql;
        ObservableList<Reservation> list = FXCollections.observableArrayList();

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "123123");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(new Reservation(rs.getInt("idreservations"),rs.getString("reservation"),rs.getString("date"),rs.getString("start_Time"),rs.getString("end_time"),rs.getString("gender")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void cancelReservation(ActionEvent event, String ID, String reservation, String gender, String date, String startTime, String endTime){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "123123");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reservations WHERE idreservations ="+ID);
            statement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Reservation Deleted Successfully");
            alert.show();
            Stage stage;
            Parent root = null;
            try {
                root = FXMLLoader.load(DB.class.getResource("/mainPanel.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            String subject = "Reservation_Canceled";
            String body = "Dear:"+DB.getUsername()+"_Sorry,_your_reservation_is_now_canceled";
            URI msg = new URI("mailto:"+DB.getUsername()+"@KFUPM.edu.sa?subject="+subject+"&body="+body);
            Desktop.getDesktop().mail(msg);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}

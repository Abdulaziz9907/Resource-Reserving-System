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

import java.io.IOException;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            new ProcessBuilder("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\OUTLOOK.EXE\"",
                    "/m", DB.getUsername()+"@kfupm.edu.sa",
                    "/a","C:\\Users\\zyadm\\IdeaProjects\\untitled13\\src\\mail").start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

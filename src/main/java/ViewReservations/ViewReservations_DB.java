package ViewReservations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ViewReservations_DB {

    public static ObservableList<Reservation> getReservations(){
        String sql;
        ObservableList<Reservation> list = FXCollections.observableArrayList();

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "12345678");
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

    public static void cancelReservation(String ID, String reservation, String gender, String date, String startTime, String endTime){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "123123");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reservations WHERE idreservations ="+ID);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

package ShowEvents;

import Login.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class ShowEvents_DB {
    public static Connection connect(){


        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "123123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void registerUser(String ID, String facility, String participants, String date, String startTime, String endTime){
        String sql;
        if (DB.getGender().equals("male"))
            sql = "SELECT event_id FROM male_events_reservations WHERE username = ?";
        else sql = "SELECT event_id FROM female_events_reservations WHERE username = ?";


        Connection connection = connect();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, Integer.parseInt(ID));
            ps.setString(1, DB.getUsername());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                while(true){
                    if(ID.equals(rs.getString("event_id"))){
                        System.out.println("Conflict");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You are already a participant!");
                        alert.show();
                        return;
                    }
                    if(!rs.next())
                        break;


            }}
                String[] parts = participants.split("/");

                if(parts[0].equals(parts[1])){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Event is full!");
                    alert.show();
                    return;
                }
                System.out.println("no conflict");
                int incremented = Integer.parseInt(parts[0]) + 1;
                String updated = String.valueOf(incremented) + "/" + parts[1];

                if (DB.getGender().equals("male"))
                    sql = "UPDATE `facilities`.`male_events` SET `participants_number`='"+updated+"' WHERE `idmale_events`='"+ID+"';";
                else sql = "UPDATE `facilities`.`female_events` SET `participants_number`='"+updated+"' WHERE `idfemale_events`='"+ID+"';";
                ps = connection.prepareStatement(sql);
                ps.executeUpdate();

                if (DB.getGender().equals("male"))
                    sql = "INSERT INTO male_events_reservations (username, event_id)\" + \"VALUES (?, ?)";
                else sql = "INSERT INTO female_events_reservations (username, event_id)" + "VALUES (?, ?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, DB.getUsername());
                ps.setString(2, ID);
                ps.executeUpdate();






        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Event> getEvents(){
        String sql;
        ObservableList<Event> list = FXCollections.observableArrayList();
        if (DB.getGender().equals("male"))
            sql = "SELECT * FROM male_events";
        else sql = "SELECT * FROM female_events";
        Connection connection = connect();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(DB.getGender().equals("male")){
                while(rs.next()){
                    list.add(new Event(rs.getInt("idmale_events"),rs.getString("facility"),rs.getString("participants_number"),rs.getString("date"),rs.getString("start_time"),rs.getString("end_time")));
                }
            }else{
                while(rs.next()){
                    list.add(new Event(rs.getInt("idfemale_events"),rs.getString("facility"),rs.getString("participants_number"),rs.getString("date"),rs.getString("start_time"),rs.getString("end_time")));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
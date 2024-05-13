package OpenEvent;

import Login.DB;
import javafx.scene.control.Alert;

import java.sql.*;

public class events_DB {
    private static boolean isBooked = false;


    public static void openEvent(String facility, String participants, String date, String startTime, String endTime){
        String sql;
        Connection connection = null;
        ResultSet re = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "123123");

            if (DB.getGender().equals("male"))
                sql = "SELECT date, start_time, end_time FROM male_events WHERE facility = ?";
            else sql = "SELECT date, start_time, end_time FROM female_events WHERE facility = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, facility);
            re = statement.executeQuery();

            if(re.isBeforeFirst()){
                while(re.next()) {

                    if(re.getString("date").equals(date)){
                        if(re.getString("start_time").equals(startTime)){
                            giveAlert();
                            return;
                        }
                        if((Integer.parseInt(startTime.substring(0,2)) < Integer.parseInt(re.getString("end_time").substring(0,2)) && Integer.parseInt(startTime.substring(0,2)) > Integer.parseInt(re.getString("start_time").substring(0,2)))
                         || (Integer.parseInt(endTime.substring(0,2)) < Integer.parseInt(re.getString("end_time").substring(0,2)) && Integer.parseInt(endTime.substring(0,2)) > Integer.parseInt(re.getString("start_time").substring(0,2)))){
                            giveAlert();
                            setIsBooked(true);
                            return;
                        }

                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (DB.getGender().equals("male"))
            sql = "INSERT INTO male_events (facility, participants_number, date, start_time, end_time)" + "VALUES (?, ?, ?, ?, ?)";
        else sql = "INSERT INTO female_events (facility, participants_number, date, start_time, end_time)" + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, facility);
            statement.setInt(2, Integer.parseInt(participants));
            statement.setString(3, date);
            statement.setString(4, startTime);
            statement.setString(5, endTime);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void giveAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Facility is booked at entered time");
        alert.show();
    }

    public static void setIsBooked(boolean isBooked) {
        events_DB.isBooked = isBooked;
    }

    public static boolean getIsBooked() {
        return isBooked;
    }
}

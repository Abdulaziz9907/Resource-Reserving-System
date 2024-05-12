package OpenEvent;

import Login.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class events_DB {

    public static void openEvent(String facility, String participants, String date, String time, String duration){
        String sql;
        if (DB.getGender().equals("male"))
            sql = "INSERT INTO male_events (facility, participants_number, date, time, duration)" + "VALUES (?, ?, ?, ?, ?)";
        else sql = "INSERT INTO female_events (facility, participants_number, date, time, duration)" + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "123123");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, facility);
            statement.setInt(2, Integer.parseInt(participants));
            statement.setString(3, date);
            statement.setString(4, time);
            statement.setString(5, duration);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

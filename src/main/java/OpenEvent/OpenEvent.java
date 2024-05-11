package OpenEvent;

import java.sql.*;

public class OpenEvent {

    public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/facilities",
                    "root",
                    "123123"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM facilities.facility_table");

            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("location"));
                System.out.println(resultSet.getString("isAvailable"));
                System.out.println(resultSet.getString("capacity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

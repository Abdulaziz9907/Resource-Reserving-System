package ReserveFacilities;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;


public class DataBase_ReserveFacilities {


    public static void FaciltiesReservation(ActionEvent event, String facilityName, String facilityLocation, String reservingTime, String gender ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;

        


        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resource reserving system", "root", "12345678");
            preparedStatement = connection.prepareStatement("SELECT Facility Name, Reservation Date, Facility Location, Reserving Time, Gender FROM reservefacilities WHERE Facility Name = ?");
            preparedStatement.setString(2, facilityLocation);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Facility Name not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("provided info are incorrect");
                alert.show();

            } else {
                while (resultSet.next()) {
                    Date retrivedFacilityDate = resultSet.getDate("Facility Date");
                    String retrivedFacilityLocation = resultSet.getString("Facility Location");
                    String retrivedReservingTime= resultSet.getString("Reserving Time");
                    String retrivedGender= resultSet.getString("Gender");

                }
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }



            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}


package ReserveFacilities;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.LocalDate;
import static Login.DB.changeScene;


public class DataBase_ReserveFacilities {



    public static void FaciltiesReservation(ActionEvent event, String facilityName,java.sql.Date ReservationDate, String facilityLocation, String reservingTime, String gender ) {
        

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resource reserving system", "root", "12345678");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM reservefacilities WHERE FacilityName = ?");
            psCheckUserExists.setString(1, facilityName);
            resultSet = psCheckUserExists.executeQuery();


            if (resultSet.isBeforeFirst()) {
                System.out.println("facility is reserved, choose another faculty");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("facility is reserved, choose another faculty");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO reservefacilities (FacilityName,ReservationDate, FacilityLocation, ReservingTime,Gender) VALUES (?,?,?,?,?) ");
                psInsert.setString(1, facilityName);
                psInsert.setString(2, String.valueOf(ReservationDate));
                psInsert.setString(3, facilityLocation);
                psInsert.setString(4, reservingTime);
                psInsert.setString(5, gender);

                psInsert.executeUpdate();

                System.out.println("reservation complete");

            }


        } catch (SQLException e) {
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


package ReserveLabsClasses;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.sql.*;
import java.time.LocalDate;
import static Login.DB.changeScene;

public class ReserveLabsClasses_DB {

    public static void Reserve_CL(ActionEvent event, String RoomNumber, String ReservationType,String BuildingNumber , java.sql.Date ReservationDate, String ReservationTime, String Gender, String ExtraDetails){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resource reserving system", "root", "12345678");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM reservelabsclasses WHERE RoomNumber = ?");
            psCheckUserExists.setString(1, RoomNumber);
            resultSet = psCheckUserExists.executeQuery();


            if (resultSet.isBeforeFirst()) {
                System.out.println("facility is reserved, choose another faculty");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("facility is reserved, choose another faculty");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO reservelabsclasses (RoomNumber, ReservationType, BuildingNumber,ReservationDate, ReservationTime, Gender,ExtraDetails) VALUES (?,?,?,?,?,?,?) ");
                psInsert.setString(1, RoomNumber);
                psInsert.setString(2, ReservationType);
                psInsert.setString(3, BuildingNumber);
                psInsert.setString(4, String.valueOf(ReservationDate));
                psInsert.setString(5, ReservationTime);
                psInsert.setString(6, Gender);
                psInsert.setString(7, ExtraDetails);

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

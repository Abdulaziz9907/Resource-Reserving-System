package ReserveLabsClasses;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.sql.*;
import java.time.LocalDate;
import static Login.DB.changeScene;
import static OpenEvent.events_DB.giveAlert;
import static OpenEvent.events_DB.setIsBooked;

public class ReserveLabsClasses_DB {

    public static void Reserve_CL(ActionEvent event, String ReservationType, String BuildingNumber, String RoomNumber, java.sql.Date ReservationDate,String gender, String ReservationTime_S,String ReservationTime_E, String ExtraDetails){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resource reserving system", "root", "12345678");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM reservelabsclasses WHERE ReservationType = ?");
            psCheckUserExists.setString(1, ReservationType);
            resultSet = psCheckUserExists.executeQuery();


            if(resultSet.isBeforeFirst()){
                while(resultSet.next()) {

                    if(resultSet.getString("ReservationDate").equals(ReservationDate)){
                        if(resultSet.getString("ReservationTimeStart").equals(ReservationTime_S)){
                            giveAlert();
                            return;
                        }
                        if((Integer.parseInt(ReservationTime_S.substring(0,2)) < Integer.parseInt(resultSet.getString("ReservationTimeEnd").substring(0,2)) && Integer.parseInt(ReservationTime_S.substring(0,2)) > Integer.parseInt(resultSet.getString("ReservationTimeStart").substring(0,2)))
                                || (Integer.parseInt(ReservationTime_E.substring(0,2)) < Integer.parseInt(resultSet.getString("ReservationTimeEnd").substring(0,2)) && Integer.parseInt(ReservationTime_E.substring(0,2)) > Integer.parseInt(resultSet.getString("ReservationTimeStart").substring(0,2)))){
                            giveAlert();
                            setIsBooked(true);
                            return;
                        }

                    }
                }
            } else {
                psInsert = connection.prepareStatement("INSERT INTO reservelabsclasses (ReservationType, BuildingNumber, RoomNumber,ReservationDate, ReservationTimeStart, ReservationTimeEnd,ExtraDetails) VALUES (?,?,?,?,?,?,?) ");
                psInsert.setString(1, ReservationType);
                psInsert.setString(2, BuildingNumber);
                psInsert.setString(3, RoomNumber);
                psInsert.setString(4, String.valueOf(ReservationDate));
                psInsert.setString(5, ReservationTime_S);
                psInsert.setString(6, ReservationTime_E);
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

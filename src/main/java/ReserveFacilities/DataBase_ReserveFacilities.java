package ReserveFacilities;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.LocalDate;
import static Login.DB.changeScene;
import static OpenEvent.events_DB.giveAlert;
import static OpenEvent.events_DB.setIsBooked;


public class DataBase_ReserveFacilities {


    public static void FaciltiesReservation(ActionEvent event, String facilityName,java.sql.Date ReservationDate, String facilityLocation, String reservingTimeS,String reservingTimeE, String gender ) {
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


            if(resultSet.isBeforeFirst()){
                while(resultSet.next()) {

                    if(resultSet.getString("ReservationDate").equals(ReservationDate)){
                        if(resultSet.getString("ReservatingTimeStart").equals(reservingTimeS)){
                            giveAlert();
                            return;
                        }
                        if((Integer.parseInt(reservingTimeS.substring(0,2)) < Integer.parseInt(resultSet.getString("ReservatingTimeEnd").substring(0,2)) && Integer.parseInt(reservingTimeS.substring(0,2)) > Integer.parseInt(resultSet.getString("ReservatingTimeStart").substring(0,2)))
                                || (Integer.parseInt(reservingTimeE.substring(0,2)) < Integer.parseInt(resultSet.getString("ReservatingTimeEnd").substring(0,2)) && Integer.parseInt(reservingTimeE.substring(0,2)) > Integer.parseInt(resultSet.getString("ReservatingTimeStart").substring(0,2)))){
                            giveAlert();
                            setIsBooked(true);
                            return;
                        }

                    }
                }
            } else {
                psInsert = connection.prepareStatement("INSERT INTO reservefacilities (FacilityName,ReservationDate, FacilityLocation, ReservingTimeStart,ReservingTimeEnd,Gender) VALUES (?,?,?,?,?,?) ");
                psInsert.setString(1, facilityName);
                psInsert.setString(2, String.valueOf(ReservationDate));
                psInsert.setString(3, facilityLocation);
                psInsert.setString(4, reservingTimeS);
                psInsert.setString(5, reservingTimeE);
                psInsert.setString(6, gender);

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


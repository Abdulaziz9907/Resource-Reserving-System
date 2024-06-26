package ReserveFacilities;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.sql.*;
import java.time.LocalDate;
import static Login.DB.changeScene;
import static OpenEvent.events_DB.giveAlert;
import static OpenEvent.events_DB.setIsBooked;


public class DataBase_ReserveFacilities {


    public static void FaciltiesReservation(ActionEvent event, String facilityName,java.sql.Date ReservationDate, String reservingTimeS,String reservingTimeE, String gender, String details ) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facilities", "root", "12345678");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM reservations WHERE reservation = ?");
            psCheckUserExists.setString(1, facilityName);
            resultSet = psCheckUserExists.executeQuery();


            if(resultSet.isBeforeFirst()){
                while(resultSet.next()) {

                    if(resultSet.getString("date").equals(ReservationDate)){
                        if(resultSet.getString("start_time").equals(reservingTimeS)){
                            giveAlert();
                            return;
                        }
                        if((Integer.parseInt(reservingTimeS.substring(0,2)) < Integer.parseInt(resultSet.getString("end_time").substring(0,2)) && Integer.parseInt(reservingTimeS.substring(0,2)) > Integer.parseInt(resultSet.getString("start_time").substring(0,2)))
                                || (Integer.parseInt(reservingTimeE.substring(0,2)) < Integer.parseInt(resultSet.getString("end_time").substring(0,2)) && Integer.parseInt(reservingTimeE.substring(0,2)) > Integer.parseInt(resultSet.getString("start_time").substring(0,2)))){
                            giveAlert();
                            setIsBooked(true);
                            return;
                        }

                    }
                }
            } else {
                psInsert = connection.prepareStatement("INSERT INTO reservations (reservation, date, start_time, end_time, gender, details) VALUES (?,?,?,?,?,?) ");
                psInsert.setString(1, facilityName);
                psInsert.setString(2, String.valueOf(ReservationDate));
                psInsert.setString(3, reservingTimeS);
                psInsert.setString(4, reservingTimeE);
                psInsert.setString(5, gender);
                psInsert.setString(6, details);

                psInsert.executeUpdate();

                System.out.println("reservation complete");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reservation Complete");
                alert.setHeaderText(null);
                alert.setContentText("Your reservation is complete!");
                alert.showAndWait();

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


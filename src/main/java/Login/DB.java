package Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class DB {

    public static void changeScene(ActionEvent event,String fxmlFile, String title, String username ,String gender, String role ){

        Parent root = null;

       try{
           root = FXMLLoader.load(DB.class.getResource(fxmlFile));
       } catch (IOException e) {
           e.printStackTrace();
       }

    Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setTitle(title);
    stage.setScene(new Scene(root,600,400));
    stage.show();

    }

    public static void signUp(ActionEvent event, String username, String password,String gender, String role) {

        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resource reserving system", "root", "admin");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE userName = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("UserName already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (userName,password, gender, role VALUES (?,?,?,?) ");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, gender);
                psInsert.setString(4, role);
                psInsert.executeUpdate();

                changeScene(event, "mainPanel.fxml", "Main Panel", username, gender, role);


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

            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
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

    public static void logIn(ActionEvent event, String username, String password){

        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/resource reserving system", "root", "admin");
            preparedStatement=connection.prepareStatement("SELECT password, gender")


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
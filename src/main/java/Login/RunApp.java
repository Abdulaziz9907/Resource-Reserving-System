package Login;

import MainPanel.MainPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class RunApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("/MainLogin.fxml"));
        stage.setTitle("Log in");
        stage.setScene(new Scene( root,600,400));




        stage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }
}
package OpenEvent;

import Login.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenEventConfirmed {
    private Stage stage;
    public void handle(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(DB.class.getResource("/OpenEvent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void returnScene(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(DB.class.getResource("/mainPanel.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

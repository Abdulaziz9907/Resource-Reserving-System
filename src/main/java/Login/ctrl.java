package Login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class ctrl implements Initializable {

    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;
    @FXML
    private TextField username_tf;
    @FXML
    private TextField pass_tf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DB.logIn(event,username_tf.getText(),pass_tf.getText());
            }
        });

        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DB.changeScene(event,"/SignUp.fxml", "Sign up", null,null, null);
            }
        });



    }


}

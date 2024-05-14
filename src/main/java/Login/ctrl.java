package Login;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    @FXML
    private Text promote_text;

    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView arrowImageView;

    @FXML
    private ImageView arrowImageView1;

    private TranslateTransition translateTransition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DB.logIn(event, username_tf.getText(), pass_tf.getText());
            }
        });

        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DB.changeScene(event, "/SignUp.fxml", "Sign up", null, null, null);
            }
        });


        arrowImageView.setTranslateX(-30); // Move the arrow left by 30 pixels initially

        // Create TranslateTransition for moving the arrow
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(250), arrowImageView);

        // Add event handlers for mouse enter and mouse exit on the button
        login_button.setOnMouseEntered(event -> {
            // Stop any ongoing animation
            translateTransition1.stop();
            // Move the arrow right by 30 pixels and make it visible
            translateTransition1.setToX(0);
            translateTransition1.play();
        });

        login_button.setOnMouseExited(event -> {
            // Stop any ongoing animation
            translateTransition1.stop();
            // Move the arrow left by 30 pixels and hide it
            translateTransition1.setToX(-30);
            translateTransition1.setOnFinished(evt -> arrowImageView.setVisible(true));
            translateTransition1.play();


        });


        arrowImageView1.setTranslateX(-30); // Move the arrow left by 30 pixels initially

        // Create TranslateTransition for moving the arrow
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(250), arrowImageView1);

        // Add event handlers for mouse enter and mouse exit on the button
        signup_button.setOnMouseEntered(event -> {
            // Stop any ongoing animation
            translateTransition2.stop();
            // Move the arrow right by 30 pixels and make it visible
            translateTransition2.setToX(0);
            translateTransition2.play();
        });

        signup_button.setOnMouseExited(event -> {
            // Stop any ongoing animation
            translateTransition2.stop();
            // Move the arrow left by 30 pixels and hide it
            translateTransition2.setToX(-30);
            translateTransition2.setOnFinished(evt -> arrowImageView1.setVisible(true));
            translateTransition2.play();


        });


        promote_text.setTranslateX(-200);

        TranslateTransition transition3 = new TranslateTransition(Duration.seconds(1), promote_text);
        transition3.setToX(0);

        transition3.play();

    }





}




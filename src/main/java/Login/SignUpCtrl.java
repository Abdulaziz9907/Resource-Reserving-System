package Login;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpCtrl implements Initializable {

    @FXML
    private Button signup_button;
    @FXML
    private Button login_button;
    @FXML
    private RadioButton roleFacility;
    @FXML
    private RadioButton roleStudent;
    @FXML
    private RadioButton genderF;
    @FXML
    private RadioButton genderM;
    @FXML
    private TextField pass_tf;
    @FXML
    private TextField username_tf;

    @FXML
    private ImageView arrowImageView2;

    @FXML
    private ImageView arrowImageView3;

    @FXML
    private Text sgup_text;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ToggleGroup toggleGroup=new ToggleGroup();
        roleFacility.setToggleGroup(toggleGroup);
        roleStudent.setToggleGroup(toggleGroup);

        ToggleGroup toggleGroup2=new ToggleGroup();
        genderF.setToggleGroup(toggleGroup2);
        genderM.setToggleGroup(toggleGroup2);

        roleStudent.setSelected(true);
        genderM.setSelected(true);

        signup_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName= ((RadioButton)toggleGroup.getSelectedToggle()).getText();
                String toggleName2= ((RadioButton)toggleGroup2.getSelectedToggle()).getText();

                if(!username_tf.getText().trim().isEmpty() && !pass_tf.getText().trim().isEmpty()){
                    DB.signUp(event,username_tf.getText(),pass_tf.getText(),toggleName2,toggleName);
                }

                else{

                    System.out.println("fill all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("fill all the information");
                    alert.show();
                }



            }
        });


        login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DB.changeScene(event,"/MainLogin.fxml","Log in",null,null,null);
            }
        });


        arrowImageView2.setTranslateX(-30); // Move the arrow left by 30 pixels initially

        // Create TranslateTransition for moving the arrow
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(250), arrowImageView2);

        // Add event handlers for mouse enter and mouse exit on the button
        signup_button.setOnMouseEntered(event -> {
            // Stop any ongoing animation
            translateTransition1.stop();
            // Move the arrow right by 30 pixels and make it visible
            translateTransition1.setToX(0);
            translateTransition1.play();
        });

        signup_button.setOnMouseExited(event -> {
            // Stop any ongoing animation
            translateTransition1.stop();
            // Move the arrow left by 30 pixels and hide it
            translateTransition1.setToX(-30);
            translateTransition1.setOnFinished(evt -> arrowImageView2.setVisible(true));
            translateTransition1.play();


        });


        arrowImageView3.setTranslateX(-30); // Move the arrow left by 30 pixels initially

        // Create TranslateTransition for moving the arrow
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(250), arrowImageView3);

        // Add event handlers for mouse enter and mouse exit on the button
        login_button.setOnMouseEntered(event -> {
            // Stop any ongoing animation
            translateTransition2.stop();
            // Move the arrow right by 30 pixels and make it visible
            translateTransition2.setToX(0);
            translateTransition2.play();
        });

        login_button.setOnMouseExited(event -> {
            // Stop any ongoing animation
            translateTransition2.stop();
            // Move the arrow left by 30 pixels and hide it
            translateTransition2.setToX(-30);
            translateTransition2.setOnFinished(evt -> arrowImageView3.setVisible(true));
            translateTransition2.play();


        });

        sgup_text.setTranslateX(-300);

        TranslateTransition transition3 = new TranslateTransition(Duration.seconds(1), sgup_text);
        transition3.setToX(0);

        transition3.setInterpolator(Interpolator.SPLINE(0.25, 0.1, 0.25, 1));

        transition3.play();




    }



}

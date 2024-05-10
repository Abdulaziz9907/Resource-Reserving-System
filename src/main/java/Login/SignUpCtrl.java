package Login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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




    }



}

package controllers;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

public class SignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton goBack;

    @FXML
    private JFXTextField signUpFirstName;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXPasswordField signUpConfirm;

    @FXML
    private JFXTextField signUpLastName;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField signUpEmail;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXTextField signUpUserName;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            if (signUpPassword.getText().equals(signUpConfirm.getText())){
                createUser();
            } else {
                System.out.println("Confirm your password!");
            }
        });
        goBack.setOnAction(event -> {
            //take user to signUp Screen
            signUpButton.getScene().getWindow().hide();
            FXMLLoader signUpWindow = new FXMLLoader();
            signUpWindow.setLocation(getClass().getResource("../view/logIn.fxml"));
            try {
                signUpWindow.load();
            } catch (IOException e) { e.printStackTrace(); }

            Parent root = signUpWindow.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("img/web_hi_res_512.png"));
            stage.setTitle("What 2-Do");
            stage.setResizable(false);
            stage.show();
        });
    }

    private void createUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstname = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = signUpUserName.getText();
        String eMail = signUpEmail.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();

        User user = new User(firstname, lastName, userName, eMail, password, location);

        databaseHandler.signUpUser(user);
    }
}

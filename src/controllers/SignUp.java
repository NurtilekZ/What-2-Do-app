package controllers;

import Database.DatabaseHandler;
import animations.Shaker;
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
            if (!signUpUserName.getText().equals("") & signUpPassword.getText().equals(signUpConfirm.getText())){
                createUser();
                signUpButton.getScene().getWindow().hide();
            } else {
                Shaker shaker = new Shaker(signUpButton);
                shaker.shake();
                System.out.println("Fill in all fields!!!");
            }
        });
        goBack.setOnAction(event -> signUpButton.getScene().getWindow().hide());
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

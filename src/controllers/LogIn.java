package controllers;

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

public class LogIn {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton logInSignUpButton;

    @FXML
    private JFXTextField logInUsername;

    @FXML
    private JFXButton logInButton;

    @FXML
    private JFXPasswordField logInPassword;

    @FXML
    void initialize() {

        logInSignUpButton.setOnAction(event -> {
            //take user to signUp Screen
            logInSignUpButton.getScene().getWindow().hide();
            FXMLLoader signUpWindow = new FXMLLoader();
            signUpWindow.setLocation(getClass().getResource("../view/signUp.fxml"));
            try {
                signUpWindow.load();
            } catch (IOException e) { e.printStackTrace(); }

            Parent root = signUpWindow.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("img/web_hi_res_512.png"));
            stage.setTitle("Sign Up");
            stage.setResizable(false);
            stage.showAndWait();
        });

        logInButton.setOnAction(event -> {

            String user = logInUsername.getText().trim();
            String pwd = logInPassword.getText().trim();

            if (!user.equals("") || !pwd.equals("")){
                loginUser(user,pwd);
            } else {
                System.out.println("Error login in user");
            }
        });
    }

    private void loginUser(String username, String password) {
        //Check in DB if given user exists, if true
        //we take them to AddItem Screen;

    }
}

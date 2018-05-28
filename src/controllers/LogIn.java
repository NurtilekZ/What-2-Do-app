package controllers;

import Database.DatabaseHandler;
import animations.Shaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

public class LogIn {

    @FXML
    private JFXButton logInSignUpButton;

    @FXML
    private JFXTextField logInUsername;

    @FXML
    private JFXButton logInButton;

    @FXML
    private JFXPasswordField logInPassword;

    private DatabaseHandler databaseHandler;
    private int userId;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        logInButton.setOnAction(event -> {
            if (logInUsername != null & logInPassword != null) {
                String userName = logInUsername.getText().trim();
                String pwd = logInPassword.getText().trim();

                User user = new User();
                user.setUserName(userName);
                user.setPassword(pwd);

                ResultSet userRow = databaseHandler.getUser(user);
                int counter = 0;

                try {
                    while (userRow.next()) {
                        counter++;
                        String name = userRow.getString("firstname");
                        userId = userRow.getInt("userid");
                    }

                    if (counter == 1) {
                        switchToMainBoardScreen();
                    } else {
                       incorrectInputReminder();
                    }
                } catch (SQLException e) { }
            } else {
                incorrectInputReminder();
            }
        });
        logInSignUpButton.setOnAction(event -> showSignUpScreen());
    }

    private void switchToMainBoardScreen() throws SQLException {
        //take user to Main Board Screen
        logInButton.getScene().getWindow().hide();
        FXMLLoader mainBoardScreen = new FXMLLoader();
        mainBoardScreen.setLocation(getClass().getResource("/view/mainBoard.fxml"));
        try {
            mainBoardScreen.load();
        } catch (IOException e) { e.printStackTrace(); }

        Parent root = mainBoardScreen.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/web_hi_res_512.png"));
        stage.setTitle("What 2-Do");
        stage.setResizable(false);
        stage.setHeight(570);
        stage.setWidth(755);

        MainBoard mainBoard = mainBoardScreen.getController();
        mainBoard.setUserId(userId);

        stage.show();
    }
    private void showSignUpScreen(){
        //take user to signUp Screen
        FXMLLoader signUpWindow = new FXMLLoader();
        signUpWindow.setLocation(getClass().getResource("/view/signUp.fxml"));
        try {
            signUpWindow.load();
        } catch (IOException e) { e.printStackTrace(); }

        Parent root = signUpWindow.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/web_hi_res_512.png"));
        stage.setTitle("Sign Up");
        stage.setResizable(false);
        stage.initOwner(logInSignUpButton.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void incorrectInputReminder(){
        Shaker shaker = new Shaker(logInUsername);
        Shaker shaker2 = new Shaker(logInPassword);
        shaker.shake();
        shaker2.shake();
    }
}

package controllers;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.security.acl.Owner;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Settings {

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton deleteUserButton;

    @FXML
    private JFXButton signOutButton;
    private DatabaseHandler databaseHandler;
    private int UserId;
    private boolean out;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> backButton.getScene().getWindow().hide());
        signOutButton.setOnAction(event -> {
            setOut(true);
            switchToLogInScreen();
        });
        deleteUserButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete account");
            alert.setContentText("Are you sure to delete this account?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK){
                databaseHandler = new DatabaseHandler();
                try {
                    databaseHandler.deleteAllTasks(getUserId());
                } catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
                setOut(true);
            } else {
                alert.close();
            }
        });
    }

    private void switchToLogInScreen() {
        //take user to Log In Screen
        signOutButton.getScene().getWindow().hide();
        FXMLLoader logInScreen = new FXMLLoader();
        logInScreen.setLocation(getClass().getResource("/view/logIn.fxml"));
        try {
            logInScreen.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = logInScreen.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/web_hi_res_512.png"));
        stage.setTitle("What 2-Do");
        stage.show();
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
    public int getUserId() {
        return UserId;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
    public boolean getOut(){
        return out;
    }
}

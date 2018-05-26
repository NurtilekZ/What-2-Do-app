package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainBoard.fxml"));
        primaryStage.setTitle("What 2-Do");
        primaryStage.getIcons().add(new Image("img/web_hi_res_512.png"));
//        primaryStage.setHeight(570);
//        primaryStage.setWidth(755);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Task;

public class MainBoard {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton todayButton;

    @FXML
    private JFXButton yesterdayButton;

    @FXML
    private JFXButton morrowButton;

    @FXML
    private JFXButton overdueButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXListView<Task> tasksListView;

    @FXML
    private JFXButton addEventButton;
    public static int userId;

    private ObservableList<Task> tasks;

    @FXML
    void initialize() {
        Task myTask = new Task();
        myTask.setTask("Clean workspace");
        myTask.setNotes("sooo shine, so bright");
        myTask.setDate(Date.valueOf("2018-05-28"));

        tasks = FXCollections.observableArrayList();
        tasks.add(myTask);

        tasksListView.setItems(tasks);
        tasksListView.setCellFactory(Cell -> new Cell());

        addEventButton.setOnAction(event -> showAddNewTaskScreen());
    }

    private void showAddNewTaskScreen(){
        //show user Add New Task Screen
        MainBoard.userId = getUserId();
        FXMLLoader addNewTaskScreen = new FXMLLoader();
        addNewTaskScreen.setLocation(getClass().getResource("../view/addNewTask.fxml"));
        try {
            addNewTaskScreen.load();
        } catch (IOException e) { e.printStackTrace(); }

        Parent root = addNewTaskScreen.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/web_hi_res_512.png"));
        stage.setTitle("Add Task");
        stage.initOwner(addEventButton.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        AddNewTask addNewTask = addNewTaskScreen.getController();
        addNewTask.setUserId(getUserId());
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }
}


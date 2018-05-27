package controllers;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Task;

public class MainBoard {

    @FXML
    private JFXButton todayButton;

    @FXML
    private JFXButton morrowButton;

    @FXML
    private JFXButton allTasksButton;

    @FXML
    private JFXButton overdueButton;

    @FXML
    private JFXButton refreshButton;

    @FXML
    private JFXButton settingsButton;

    @FXML
    private JFXListView<Task> tasksListView;

    @FXML
    private JFXButton addEventButton;

    public static int userId;
    private ObservableList<Task> todayTasks;
    private ObservableList<Task> morrowTasks;
    private ObservableList<Task> overdueTasks;
    private ObservableList<Task> allTasks;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() throws SQLException{
        updateList();

        allTasksButton.setOnAction(event -> {
            tasksListView.getItems().removeAll();
            setListViews(tasksListView, allTasks);
        });
        todayButton.setOnAction(event -> {
            tasksListView.getItems().removeAll();
            setListViews(tasksListView,todayTasks);
        });
        morrowButton.setOnAction(event -> {
            tasksListView.getItems().removeAll();
            setListViews(tasksListView,morrowTasks);
        });
        overdueButton.setOnAction(event -> {
            tasksListView.getItems().removeAll();
            setListViews(tasksListView,overdueTasks);
        });
        addEventButton.setOnAction(event -> {
            showAddNewTaskScreen();
            try {
                updateList();
            } catch (SQLException e) { e.printStackTrace(); }
        });
        refreshButton.setOnAction(event -> {
            try {
                updateList();
            } catch (SQLException e) { e.printStackTrace();}
        });
        settingsButton.setOnAction(event -> showSettingsScreen());
    }

    private void showSettingsScreen() {
        //take user to Settings Screen
        FXMLLoader settingsScreen = new FXMLLoader();
        settingsScreen.setLocation(getClass().getResource("/view/settings.fxml"));
        try {
            settingsScreen.load();
        } catch (IOException e) { e.printStackTrace(); }

        Parent root = settingsScreen.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/web_hi_res_512.png"));
        stage.setTitle("Setting");
        stage.setResizable(false);
        stage.initOwner(settingsButton.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);

        Settings settings = settingsScreen.getController();
        settings.setUserId(MainBoard.userId);
        stage.showAndWait();

        if (settings.getOut()) {
            settingsButton.getScene().getWindow().hide();
        }
    }
    private void showAddNewTaskScreen(){
        //show user Add New Task Screen
        MainBoard.userId = getUserId();
        FXMLLoader addNewTaskScreen = new FXMLLoader();
        addNewTaskScreen.setLocation(getClass().getResource("/view/addNewTask.fxml"));
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
    public void updateList() throws SQLException {
        allTasks = FXCollections.observableArrayList();
        todayTasks = FXCollections.observableArrayList();
        morrowTasks = FXCollections.observableArrayList();
        overdueTasks = FXCollections.observableArrayList();

        databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTaskByUser(MainBoard.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDate(resultSet.getDate("date"));
            task.setNotes(resultSet.getString("notes"));
            task.setLocation(resultSet.getString("location"));
            task.setDone(resultSet.getBoolean("done"));
            if (resultSet.getDate("date") != null &&
                    resultSet.getDate("date").toLocalDate().isEqual(LocalDate.now())) {
                todayTasks.add(task);
            } else if (resultSet.getDate("date") != null &&
                    resultSet.getDate("date").toLocalDate().isBefore(LocalDate.now())) {
                overdueTasks.add(task);
            } else if (resultSet.getDate("date") != null &&
                    resultSet.getDate("date").toLocalDate().isAfter(LocalDate.now())) {
                morrowTasks.add(task);
            }
            allTasks.addAll(task);
        }
        setListViews(tasksListView, todayTasks);
    }
    private void setListViews(JFXListView<Task> listView, ObservableList<Task> tasks){
            listView.setItems(tasks);
            listView.setCellFactory(Cell -> new Cell());
    }

    public void setUserId(int givenUserId) throws SQLException {
        userId = givenUserId;
        initialize();
    }
    public int getUserId() {
        return userId;
    }
}

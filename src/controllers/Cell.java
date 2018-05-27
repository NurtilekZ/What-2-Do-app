package controllers;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXListCell;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Task;

public class Cell extends JFXListCell<Task> {

    @FXML
    private AnchorPane rootCellPane;

    @FXML
    private FontAwesomeIconView checkButton;

    @FXML
    private Label taskName;

    @FXML
    private Label noteText;

    @FXML
    private Label date;

    @FXML
    private FontAwesomeIconView updateTask;

    @FXML
    private FontAwesomeIconView deleteButton;

    private FXMLLoader fxmlLoader;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task myTask, boolean empty) {
        databaseHandler = new DatabaseHandler();
        super.updateItem(myTask, empty);
        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) { e.printStackTrace();}
            }
            taskName.setText(myTask.getTask());
            noteText.setText(myTask.getNotes());
            if (myTask.getDate() != null) {
                date.setText(myTask.getDate().toString());
            } else { date.setText(""); }
            int taskId = myTask.getTaskId();
            if (!myTask.isDone()){
                checkButton.setFill(Paint.valueOf("#c6c6c6"));
            } else {
                checkButton.setFill(Paint.valueOf("#64dd17"));
            }

            updateTask.setOnMouseClicked(event -> showUpdateTaskScreen(myTask));
            deleteButton.setOnMouseClicked(event -> deleteTask(taskId));

            checkButton.setOnMouseClicked(event -> {
                if (myTask.isDone()){
                    myTask.setDone(false);
                    checkButton.setFill(Paint.valueOf("#c6c6c6"));
                } else {
                    myTask.setDone(true);
                    checkButton.setFill(Paint.valueOf("#64dd17"));
                }
                try {
                    databaseHandler.updateTask(myTask);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            setText(null);
            setGraphic(rootCellPane);
        }
    }
    private void showUpdateTaskScreen(Task task){
        //show user Update Task Screen
        FXMLLoader updateTaskScreen = new FXMLLoader();
        updateTaskScreen.setLocation(getClass().getResource("/view/updateTask.fxml"));
        try {
            updateTaskScreen.load();
        } catch (IOException e) { e.printStackTrace(); }

        Parent root = updateTaskScreen.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.getIcons().add(new Image("img/web_hi_res_512.png"));
        stage.setTitle(taskName.getText().trim());
        stage.initOwner(updateTask.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);

        UpdateTask updateTask = updateTaskScreen.getController();
        updateTask.setTaskName(task.getTask());
        updateTask.setDate(task.getDate().toLocalDate());
        updateTask.setNote(task.getNotes());

        updateTask.updateTaskButton.setOnAction(event -> {
            task.setTask(updateTask.getTaskName().getText().trim());
            task.setDate(Date.valueOf(updateTask.getDate().getValue()));
            task.setLocation(updateTask.getTaskLocation().getText().trim());
            task.setNotes(updateTask.getNote().getText().trim());
            try {
                databaseHandler.updateTask(task);
            } catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
            updateTask.updateTaskButton.getScene().getWindow().hide();
        });

        stage.showAndWait();
    }
    private void deleteTask(int taskId){
        databaseHandler = new DatabaseHandler();
        try {
            databaseHandler.deleteTask(MainBoard.userId, taskId);
        } catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
        getListView().getItems().remove(getItem());
    }
}


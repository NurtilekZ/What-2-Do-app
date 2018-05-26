package controllers;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Task;
import java.sql.Date;

public class AddNewTask {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskName;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTextField locationText;

    @FXML
    private VBox innerTaskPane;

    @FXML
    private JFXButton addInnerTaskButton;

    @FXML
    private JFXTextArea notesArea;

    @FXML
    private JFXButton saveTaskButton;

    private DatabaseHandler databaseHandler;
    private int userId;

    @FXML
    void initialize() {

        saveTaskButton.setOnAction(event -> {
            addTask();
        });
    }

    private void addTask() {
        databaseHandler = new DatabaseHandler();

        String name = taskName.getText();
        Date date = Date.valueOf(datePicker.getValue());
        String loc = locationText.getText();
        String note = notesArea.getText();

        Task task = new Task(MainBoard.userId, name,date,loc,note);

        databaseHandler.insertTask(task);
    }


    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

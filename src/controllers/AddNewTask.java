package controllers;

import Database.DatabaseHandler;
import com.jfoenix.controls.*;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Task;
import java.sql.Date;

public class AddNewTask {

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
    private JFXTextField innerTaskDescription;

    @FXML
    private JFXTextArea notesArea;

    @FXML
    private JFXButton saveTaskButton;

    private DatabaseHandler databaseHandler;
    private int userId;

    @FXML
    void initialize() {

        saveTaskButton.setOnAction(event -> {
            if (!taskName.getText().equals("")){
                addTask();
                saveTaskButton.getScene().getWindow().hide();
            }
        });
        addInnerTaskButton.setOnAction(event -> {
            if (!innerTaskDescription.getText().equals("")){
                JFXCheckBox innerTask = new JFXCheckBox(innerTaskDescription.getText());
                FontAwesomeIconView del = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                HBox innerPane = new HBox(10);
                del.setOnMouseClicked(event1 -> innerTaskPane.getChildren().remove(del.getParent()));
                innerPane.getChildren().addAll(innerTask, del);
                innerTaskPane.getChildren().add(innerPane);
                innerTaskDescription.setText("");
            }
        });
    }

    private void addTask() {
        databaseHandler = new DatabaseHandler();

        String name = taskName.getText().trim();
        Date date = null;
        if (datePicker.getValue() != null) {
            date = Date.valueOf(datePicker.getValue());
        }
        String loc = locationText.getText().trim();
        String note = notesArea.getText().trim();

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

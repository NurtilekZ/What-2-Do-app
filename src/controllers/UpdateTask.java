package controllers;

import Database.DatabaseHandler;
import com.jfoenix.controls.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Task;
import java.sql.Date;

public class UpdateTask {

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTextField taskLocation;

    @FXML
    private VBox innerTaskPane;

    @FXML
    private JFXTextField innerTaskDescription;

    @FXML
    private JFXButton addInnerTaskButton;

    @FXML
    private JFXTextArea note;

    @FXML
    public JFXButton updateTaskButton;

    @FXML
    private JFXTextField taskName;

    private DatabaseHandler databaseHandler;
    private int userId;

    @FXML
    void initialize() {
    }

    public JFXDatePicker getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }

    public JFXTextField getTaskLocation() {
        return taskLocation;
    }
    public void setTaskLocation(JFXTextField taskLocation) {
        this.taskLocation = taskLocation;
    }

    public VBox getInnerTaskPane() {
        return innerTaskPane;
    }
    public void setInnerTaskPane(VBox innerTaskPane) {
        this.innerTaskPane = innerTaskPane;
    }

    public JFXTextField getInnerTaskDescription() {
        return innerTaskDescription;
    }
    public void setInnerTaskDescription(JFXTextField innerTaskDescription) {
        this.innerTaskDescription = innerTaskDescription;
    }

    public JFXButton getAddInnerTaskButton() {
        return addInnerTaskButton;
    }
    public void setAddInnerTaskButton(JFXButton addInnerTaskButton) {
        this.addInnerTaskButton = addInnerTaskButton;
    }

    public JFXTextArea getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note.setText(note);
    }

    public JFXTextField getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName.setText(taskName);
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
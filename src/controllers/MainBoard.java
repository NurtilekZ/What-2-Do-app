package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

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
    private FontAwesomeIconView settingsButton;

    @FXML
    private JFXListView<?> tasksListView;

    @FXML
    private VBox detailsPane;

    @FXML
    private JFXTextField taskName;

    @FXML
    private JFXTextField Date;

    @FXML
    private JFXTextField locationText;

    @FXML
    private JFXButton addInnerTaskButton;

    @FXML
    private JFXTextArea notesArea;

    @FXML
    private FontAwesomeIconView addEventButton;

    @FXML
    void initialize() {
        assert todayButton != null : "fx:id=\"todayButton\" was not injected: check your FXML file 'mainBoard.view'.";
        assert yesterdayButton != null : "fx:id=\"yesterdayButton\" was not injected: check your FXML file 'mainBoard.view'.";
        assert morrowButton != null : "fx:id=\"morrowButton\" was not injected: check your FXML file 'mainBoard.view'.";
        assert overdueButton != null : "fx:id=\"overdueButton\" was not injected: check your FXML file 'mainBoard.view'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'mainBoard.view'.";
        assert tasksListView != null : "fx:id=\"tasksListView\" was not injected: check your FXML file 'mainBoard.view'.";
        assert detailsPane != null : "fx:id=\"detailsPane\" was not injected: check your FXML file 'mainBoard.view'.";
        assert taskName != null : "fx:id=\"taskName\" was not injected: check your FXML file 'mainBoard.view'.";
        assert Date != null : "fx:id=\"Date\" was not injected: check your FXML file 'mainBoard.view'.";
        assert locationText != null : "fx:id=\"locationText\" was not injected: check your FXML file 'mainBoard.view'.";
        assert addInnerTaskButton != null : "fx:id=\"addInnerTaskButton\" was not injected: check your FXML file 'mainBoard.view'.";
        assert notesArea != null : "fx:id=\"notesArea\" was not injected: check your FXML file 'mainBoard.view'.";
        assert addEventButton != null : "fx:id=\"addEventButton\" was not injected: check your FXML file 'mainBoard.view'.";

    }
}

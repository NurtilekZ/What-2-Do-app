package controllers;

import com.jfoenix.controls.JFXListCell;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Task;

public class Cell extends JFXListCell<Task> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private FontAwesomeIconView deleteButton;

    private FXMLLoader fxmlLoader;
    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task myTask, boolean empty) {
        super.updateItem(myTask, empty);

        if (empty || myTask == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../view/cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) { e.printStackTrace();}
            }

            taskName.setText(myTask.getTask());
            noteText.setText(myTask.getNotes());
            date.setText(myTask.getDate().toString());

            setText(null);
            setGraphic(rootCellPane);
        }
    }
}


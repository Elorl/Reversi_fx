package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable{

    @FXML
    private ChoiceBox boardSizesBox;

    @FXML
    private ObservableList<String> boardSizesList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize board sizes list 4*4 to 20*20
        for(int i = 4; i <= 20; i++) {
            boardSizesList.add(String.valueOf(i) + "x" + String.valueOf(i));
        }

        //insert list to board size box
        boardSizesBox.setItems(boardSizesList);
    }



    public void settingsAction() throws IOException {
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Settings");
        Parent settingsNode = FXMLLoader.load(getClass().getResource("settings.fxml"));
        settingsStage.setScene(new Scene(settingsNode));
        settingsStage.show();
    }
}

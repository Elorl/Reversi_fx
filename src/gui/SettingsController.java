package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.lang.Exception;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    public Stage settingsStage;
    @FXML
    private ChoiceBox boardSizesBox;
    @FXML
    private ObservableList<String> boardSizesList = FXCollections.observableArrayList();
    @FXML
    private Button saveBtn;
    @FXML
    private ColorPicker player1Color;
    @FXML
    private ColorPicker player2Color;
    @FXML
    private AnchorPane ap = new AnchorPane();
    @FXML
    private Label comment = new Label();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize board sizes list 4*4 to 20*20
        for (int i = 4; i <= 20; i++) {
            boardSizesList.add(String.valueOf(i) + "x" + String.valueOf(i));
            i++;
        }
        //insert list to board size box
        boardSizesBox.setItems(boardSizesList);
        //initialize the list of players, to choose who will start.
    }


    public void settingsAction() throws IOException {
        this.settingsStage = new Stage();
        this.settingsStage.initModality(Modality.APPLICATION_MODAL);
        this.settingsStage.setTitle("Settings");
        Parent settingsNode = FXMLLoader.load(getClass().getResource("settings.fxml"));
        this.settingsStage.setScene(new Scene(settingsNode));
        this.settingsStage.show();

    }

    public void saveVal() {
        Color colorP1, colorP2;
        colorP1 = this.player1Color.getValue();
        colorP2 = this.player2Color.getValue();
        String size;
        try {
            size = this.boardSizesBox.getValue().toString();
        } catch (Exception NullPointerException) {
            AlertBox.display("something's missing. did you forgot to fill something?");
            this.settingsStage = (Stage) this.saveBtn.getScene().getWindow();
            return;
        }
        if (!colorP1.toString().equals(colorP2.toString())) {
            File settingsFIle = new File("settings.txt");
            String[] num = size.split("x");
            String str = colorP1.toString() + " " + colorP2.toString() + " " + num[0];
            try {
                FileOutputStream file = new FileOutputStream(settingsFIle.getName());
                PrintStream writer = new PrintStream(file);
                writer.print(str);
                file.close();
                this.settingsStage = (Stage) this.saveBtn.getScene().getWindow();
                this.settingsStage.close();
            } catch (Exception FileNotFoundException) {
                throw new RuntimeException();
            }
        } else {
            AlertBox.display("Choose different color to each player!");
        }

    }
}
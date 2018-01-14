package gui;

import reversi.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private HBox root;
    @FXML
    private Button playButton;
    @FXML
    private Button settingsButton;

    @FXML
    public void playAction(ActionEvent event) throws IOException {

        Parent gameNode = FXMLLoader.load(getClass().getResource("reversiGrid.fxml"));
        //Scene scene = new Scene(gameNode);
        //Stage primaryStage = (Stage)playButton.getScene().getWindow();

        //primaryStage.setScene(scene);
    }

    @FXML
    public void settingsAction() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        loader.load();
        SettingsController settingsController = loader.getController();
        settingsController.settingsAction();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // change to reading from settings file;
        Board board = new Board(8, 8);
        Player first = new ManualPlayer(Color.BLACK);
        Player second = new ManualPlayer(Color.WHITE);
        GameLogic logic = new ManualLogic(board, first, second);
        ReversiGrid grid = new ReversiGrid(board, first, second, logic);
        grid.setPrefHeight(600);
        grid.setPrefWidth(600);
        root.getChildren().add(0, grid);
        grid.draw();
    }
}

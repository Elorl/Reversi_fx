package gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Label;
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
    private Label currentPlayerLbl = new Label("");
    @FXML
    private Label player1ScoreLbl = new Label("");
    @FXML
    private Label player2ScoreLbl = new Label("");
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

    @FXML
    public void updateLabels(Color currentPlayer, int player1Score, int player2Score) {
        currentPlayerLbl.setText(currentPlayer.toString());
        player1ScoreLbl.setText(String.valueOf(player1Score));
        player2ScoreLbl.setText(String.valueOf(player2Score));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // change to reading from settings file;
        

        //initialize grid
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

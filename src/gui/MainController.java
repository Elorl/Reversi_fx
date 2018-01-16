package gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import reversi.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable{

    ReversiGrid grid;
    @FXML
    private HBox root;
    @FXML
    private VBox sideBar;
    @FXML
    Button settingsButton;
    @FXML
    Button closeButton;
    @FXML
    private Label currentPlayerLbl;
    @FXML
    private Text player1ScoreTxt;
    @FXML
    private Label player1ScoreLbl;
    @FXML
    private Text player2ScoreTxt;
    @FXML
    private Label player2ScoreLbl;
    @FXML
    private Label gameOver;
    @FXML
    public void playAction(ActionEvent event) throws IOException {
        Parent gameNode = FXMLLoader.load(getClass().getResource("reversiGrid.fxml"));
    }

    @FXML
    public void settingsAction() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        loader.load();
        SettingsController settingsController = loader.getController();
        settingsController.settingsAction();
    }

    @FXML
    public void closeAction() {
        Stage c = (Stage) this.closeButton.getScene().getWindow();
        this.closeButton.setOnAction(e -> c.close());
        Platform.exit();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // change to reading from settings file;


        //initialize grid
        Board board = new Board(20, 20);
        Player first = new ManualPlayer(Color.BLACK);
        Player second = new ManualPlayer(Color.WHITE);
        currentPlayerLbl.setText(String.valueOf(first.getType().toString()));
        //labels updater anonymous class implements GameInfoListener is sent to the grid
        // so when event happens ,info will be updated
        GameInfoListener labelsUpdater = new GameInfoListener() {
            @Override
            public void updateInfo() {
                currentPlayerLbl.setText(grid.getTurnPlayer().getType().toString());
                player1ScoreLbl.setText(String.valueOf(first.getCount()));
                player2ScoreLbl.setText(String.valueOf(second.getCount()));
            }
            @Override
            public void alertEnd(Color winnerColor) {
                if(winnerColor == Color.EMPTY) {
                    gameOver.setText("Game Over.\nIts a draw");
                    return;
                }
                gameOver.setText("Game Over.\nWinner: " + winnerColor.toString());
            }
        };

        GameLogic logic = new ManualLogic(board, first, second);
        grid = new ReversiGrid(board, first, second, logic, labelsUpdater);
        root.getChildren().add(0, grid);

        /*handle resize
        root.widthProperty().addListener((observable, oldValue, newValue) -> { double
                boardNewWidth = newValue.doubleValue() - 120;
            grid.setPrefWidth(boardNewWidth);
            grid.draw();
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            grid.setPrefHeight(newValue.doubleValue());
            grid.draw();
        });*/
        grid.setPrefHeight(600);
        grid.setPrefWidth(600);
        grid.draw();
    }
}

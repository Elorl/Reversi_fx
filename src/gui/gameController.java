package gui;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import reversi.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class gameController implements Initializable{

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
    private Circle currentPlayerCircle;
    @FXML
    private Text player1ScoreTxt;
    @FXML
    private Circle player1ScoreCircle;
    @FXML
    private Label player1ScoreLbl;
    @FXML
    private Text player2ScoreTxt;
    @FXML
    private Circle player2ScoreCircle;
    @FXML
    private Label player2ScoreLbl;
    @FXML
    private Label gameOver;

    private javafx.scene.paint.Color colors[];

    /**
     * opening a setting windows.
     */
    @FXML
    public void settingsAction() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
        try {
            loader.load();
            SettingsController settingsController = loader.getController();
            settingsController.settingsAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * closing game window and returns to main window, menu.
     */
    @FXML
    public void closeAction() {
        try {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root, 600, 600);
            stage.setTitle("Reversi Game");
            stage.setScene(scene);
            stage.show();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //reading from settings file
        FileReader fileReader;
        try {
            fileReader = new FileReader("settings.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] settings;
        try {
            settings = bufferedReader.readLine().split(" ");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        //use decode method of java.awt to read hex code as written in file
        // then convert to javafx Color object
        java.awt.Color[] awtColors = new java.awt.Color[2];
        colors = new javafx.scene.paint.Color[2];
        String colorStr;
        for(int i = 0; i < 2; i++) {
            colorStr =  settings[i].replaceAll("0x", "#").substring(0, 7);
            awtColors[i] = java.awt.Color.decode(colorStr );
            int red = awtColors[i].getRed();
            int green = awtColors[i].getGreen();
            int blue = awtColors[i].getBlue();
            colors[i] = javafx.scene.paint.Color.rgb(red, blue, green);
        }

        // board size
        int boardSize = Integer.valueOf(settings[2]);


        //initialize grid
        Board board = new Board(boardSize, boardSize);
        Player player1 = new ManualPlayer(Color.BLACK);
        Player player2 = new ManualPlayer(Color.WHITE);
        player1ScoreCircle.setFill(colors[0]);
        player2ScoreCircle.setFill(colors[1]);
        currentPlayerCircle.setFill(colors[0]);

        //labels updater anonymous class implements GameInfoListener is sent to the grid
        // so when event happens ,info will be updated
        GameInfoListener labelsUpdater = new GameInfoListener() {
            @Override
            public void updateInfo() {
                javafx.scene.paint.Color currentColor;
                if(grid.getTurnPlayer().getType() == Color.BLACK) {
                    currentColor = colors[0];
                } else {
                    currentColor = colors[1];
                }
                currentPlayerCircle.setFill(currentColor);
                player1ScoreLbl.setText(String.valueOf(player1.getCount()));
                player2ScoreLbl.setText(String.valueOf(player2.getCount()));
            }
            @Override
            public void alertEnd(Color winnerColor) {
                if(winnerColor == Color.EMPTY) {
                    gameOver.setText("Game Over.\nIts a draw");
                    return;
                }
                String winner;
                if(winnerColor == Color.BLACK) {
                    winner = "first player";
                } else {
                    winner = "second player";
                }
                gameOver.setText("Game Over.\nWinner:\n " + winner);
            }
        };

        GameLogic logic = new ManualLogic(board, player1, player2);

        //instantiating new grid and drawing it
        grid = new ReversiGrid(board, player1, player2, logic, labelsUpdater, colors);
        root.getChildren().add(0, grid);

        grid.setPrefHeight(600);
        grid.setPrefWidth(600);

        grid.draw();
    }
}




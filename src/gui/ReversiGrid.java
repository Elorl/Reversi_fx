package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import reversi.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;


import java.io.IOException;
import java.util.List;

public class ReversiGrid extends GridPane {
    private Board board;
    private Player player1;
    private Player player2;
    private Player turnPlayer;
    private GameLogic logic;

    ReversiGrid(Board board, Player player1, Player player2, GameLogic logic){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.turnPlayer = player1;
        this.logic = logic;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reversiGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class DiskFlip implements EventHandler<MouseEvent> {
        int x;
        int y;
        DiskFlip(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void handle(MouseEvent event) {

            int counter;
            //negative color
            reversi.Color neg = turnPlayer.getType().toggle();

            counter = 0;
            logic.initializeOpt();
            System.out.println("current board:\n");
            board.printBoard();
            System.out.println(turnPlayer.getSymbol() + ": It's your move.");
            //check the options the player has.
            counter = logic.checkOpt(turnPlayer.getType());


            logic.printOpt(counter);
            //if it's not a valid move, return
            if(!logic.validOpt(this.x,this.y)) {
                return;
            }
            //if valid, start to upside down all the relevant disks.
            logic.initializeOpt();
            //upside down the relevant disks.
            logic.swap(this.x, this.y, turnPlayer.getType(), neg);
            //draw grid after flipping
            draw();
            //update points
            logic.printPoints();

            /*check next moves are possible*/
            //check move possibility of next player
            logic.initializeOpt();
            counter = logic.checkOpt(neg);

            //if next player has a move
            if(counter > 0) {
                togglePlayers();
                return;

                //there is no possible option.
            } else {
                //check if turn player has a move
                logic.initializeOpt();
                counter = logic.checkOpt(turnPlayer.getType());
                if(counter > 0) {
                    System.out.println("No possible moves. Play passes back to the other player.");
                    //return without toggling players
                    return;
                }
                //2 players have no move
                else {
                    System.out.println("No possible moves. game over.");
                }
            }
        }
        }

    /**
     * switch players.
     */
    private void togglePlayers() {
            if(turnPlayer == player1) {
                turnPlayer = player2;
            } else {
                turnPlayer = player1;
            }
        }

    /**
     * draw grid.
     */
    public void draw() {
        this.getChildren().clear();
        // set cell height and width
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();

        List<List<Cell>> boardArr = board.boardArr;
        int boardHeight = boardArr.size();
        int boardWidth = boardArr.get(0).size();

        int cellHeight = height / boardHeight;
        int cellWidth = width / boardWidth;

        for(int i = 0; i < boardHeight; i++) {
            for(int j = 0; j < boardWidth; j++) {
                //check color of the cell
                Color color;
                if(boardArr.get(i).get(j).getColor() == reversi.Color.WHITE) {
                    color = Color.WHITE;
                }
                else if(boardArr.get(i).get(j).getColor() == reversi.Color.BLACK) {
                    color = Color.BLACK;
                }
                else {
                    color = Color.YELLOW;
                }

                //add circle with corresponding color to cell
                Circle circle = new Circle(cellWidth/2, color );
                circle.setOnMouseClicked(new DiskFlip(i, j));
                this.add(circle, j, i);
            }
        }
    }
}

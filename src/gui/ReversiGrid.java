package gui;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import reversi.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Circle;


import java.io.IOException;
import java.util.List;


public class ReversiGrid extends GridPane {
    private Board board;
    private Player player1;
    private Player player2;
    private Player turnPlayer;
    private GameLogic logic;
    private GameInfoListener infoListener;
    private Color[] colors;

    /**
     * constructor
     * @param board reversi board object
     * @param player1 player1
     * @param player2 player2
     * @param logic logic
     * @param infoListener listening to info changes and update diaplay
     * @param colors colors of players
     */
    ReversiGrid(Board board, Player player1, Player player2, GameLogic logic,
                GameInfoListener infoListener, Color[] colors){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.turnPlayer = player1;
        this.logic = logic;
        this.infoListener = infoListener;
        this.colors = colors;

        // loading, and setting grid as it's own controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reversiGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getTurnPlayer() {
        return turnPlayer;
    }

    /**
     * an event handler for updating board after a click
     */
    private class DiskFlip implements EventHandler<MouseEvent> {
        int x;
        int y;

        /**
         * constructor.
         * @param x x index on grid
         * @param y y index on grid
         */
        DiskFlip(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * handles a mouse event by flipping relevant disks on grid
         * @param event mouse event
         */
        @Override
        public void handle(MouseEvent event){

            //negative color
            reversi.Color neg = turnPlayer.getType().toggle();
            int counter = 0;
            logic.initializeOpt();

            //check the options the player has.
            counter = logic.checkOpt(turnPlayer.getType());


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

            /*check next moves  possibility*/

            //check move possibility of next player
            logic.initializeOpt();
            counter = logic.checkOpt(neg);

            //if next player has a move
            if(counter > 0) {
                togglePlayers();
                infoListener.updateInfo();
                return;

                //there is no possible option.
            } else {
                //check if turn player has a move
                logic.initializeOpt();
                counter = logic.checkOpt(turnPlayer.getType());
                if(counter > 0) {
                    //return without toggling players
                    infoListener.updateInfo();
                    return;
                }
                //2 players have no move
                else {
                    infoListener.updateInfo();
                    infoListener.alertEnd(logic.getWinner());
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

        //updating grid with relevant disks by scanning the reversi board matrix
        for(int i = 0; i < boardHeight; i++) {
            for(int j = 0; j < boardWidth; j++) {
                //adding a stack pane to the grid, and circle inside it
                StackPane shadowPane = new StackPane();
                shadowPane.setPrefHeight(cellHeight);
                shadowPane.setPrefWidth(cellWidth);
                //adding a 3D effect
                shadowPane.setAlignment(Pos.BOTTOM_RIGHT);
                shadowPane.getChildren().add(new Rectangle(cellWidth, cellHeight, Color.rgb(255, 166, 77)));
                shadowPane.setBorder(new Border(new BorderStroke(Color.rgb(102, 51, 0),
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                this.add(shadowPane, i, j);

                StackPane pane = new StackPane();
                pane.setPrefHeight(cellHeight);
                pane.setPrefWidth(cellWidth);
                pane.setAlignment(Pos.CENTER);
                this.add(pane, i,j);

                //centering content in pane
                GridPane.setHalignment(pane, HPos.CENTER);
                GridPane.setValignment(pane, VPos.CENTER);

                //check color of the cell
                Color color;
                if(boardArr.get(i).get(j).getColor() == reversi.Color.BLACK) {
                    color = colors[0];
                }
                else if(boardArr.get(i).get(j).getColor() == reversi.Color.WHITE) {
                    color = colors[1];
                }
                else {
                    color = Color.TRANSPARENT;
                }
                if(!color.equals(Color.TRANSPARENT)) {
                    Circle circleShadow = new Circle((cellWidth/2) - 3, Color.BLACK.brighter().brighter().brighter().brighter());
                    shadowPane.getChildren().add(circleShadow);
                }
                //add circle with corresponding color to cell
                Circle circle = new Circle((cellWidth/2) - 3 , color );
                circle.setOnMouseClicked(new DiskFlip(i, j));
                pane.getChildren().add(circle);

            }
        }
    }
}


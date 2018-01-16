package reversi;

import java.util.ArrayList;
import java.util.List;
/**
 * ManualLogic.
 */
public class ManualLogic implements GameLogic {
    private Board board;
    public List<List<Cell>> optionsArray = new ArrayList<>();
    private Player player1;
    private Player player2;
    private int counter;

    public ManualLogic(Board board, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        //initialize the 2D array of cell's options.
        for(int row = 0; row < this.board.getRowsNum(); row++) {
            List<Cell> rowCell = new ArrayList<Cell>();
            for(int column = 0; column < this.board.getColumnsNum(); column++) {
                Cell cell = new Cell(row, column, Color.EMPTY);
                rowCell.add(cell);
            }
            this.optionsArray.add(rowCell);
        }
    }
    public void initializeOpt() {
        for(int i = 0; i < this.board.getRowsNum(); i++) {
            for(int j = 0; j < this.board.getColumnsNum(); j++) {
                this.optionsArray.get(i).get(j).setColor(Color.EMPTY);
            }
        }
    }

    public void printOpt(int count) {
        int countOfPrint = 0, counter = count;
        System.out.print("Your possible moves: ");
        for(int i = 0; i < this.board.getRowsNum(); i++) {
            for(int j = 0; j < this.board.getColumnsNum(); j++) {
                Cell cell = this.optionsArray.get(i).get(j);
                if(cell.getColor() != Color.EMPTY) {
                    cell.printCell();
                    countOfPrint++;
                    if(countOfPrint < counter) {
                        System.out.print(',');
                    }
                }
            }
        }
    }
    public int checkOpt(Color color) {
        this.counter = 0;
        Color negColor, originColor;
        for(int i = 0; i < this.board.getRowsNum(); i++) {
            for(int j = 0; j < this.board.getColumnsNum(); j++) {
                Cell cell = this.board.boardArr.get(i).get(j);
                if(cell.getColor() == color) {
                    int row = i, column = j;
                    if(color == Color.WHITE) {
                        originColor = Color.WHITE;
                        negColor = Color.BLACK;
                    } else {
                        originColor = Color.BLACK;
                        negColor = Color.WHITE;
                    }
                    this.scanOpt(row, column, originColor, negColor, Action.FIND);
                }
            }
        }
        return this.counter;
    }

    public void scanOpt(int row, int column, Color originColor, Color negColor, Action act) {
        int changeX = 0, changeY = 0;
        int rowsNum = this.board.getRowsNum();
        int columnsNum = this.board.getColumnsNum();
        for (int i = row - 1; i <= row + 1 ; ++i) {
            for (int j = column - 1; j <= column + 1 ; ++j) {
                //check if the cords is not in the board.
                if(i < 0 || i >= rowsNum || j < 0 || j >= columnsNum) {
                    continue;
                } else {
                    //check if this cell is irrelevant = empty or in the same color as the player.
                    Cell cell = this.board.boardArr.get(i).get(j);
                    if((cell.getColor() == originColor) || (cell.getColor() == Color.EMPTY)) {
                        continue;
                    } else {
                        changeX = i - row;
                        changeY = j - column;
                        //check if the next step is to continue with the search or to upside down the disks.
                        if(act == Action.FLIP) {
                            Cell c = this.board.boardArr.get(row).get(column);
                            if(!(c.getColor() == originColor)) {
                                this.optionsArray.get(row).get(column).setColor(originColor);
                            }
                            //add the cell to the option array(relevant cell).
                            //this->optionsArray[i][j].setColor(originColor);
                            this.directionUpside(i, j, changeX, changeY, originColor, negColor);
                        } else {
                            this.directionScan(i, j, changeX, changeY, originColor, negColor);
                        }
                    }
                }
            }
        }
    }


    private void directionScan(int i, int j, int changeRow, int changeColumn, Color originColor, Color negColor) {
        boolean flag = true;
        while(flag){
            i += changeRow;
            j += changeColumn;
            //check if the cell is irrelevant = the same color as the player (XOX) or not in the board.
            if(i < 0 || i >= this.board.getRowsNum()
                    || j < 0 || j >= this.board.getColumnsNum() || this.board.boardArr.get(i).get(j).getColor() == originColor) {
                return;
                //we found an empty cell after cells with negColor, so this is valid option.
            } else if(this.board.boardArr.get(i).get(j).getColor() == Color.EMPTY) {
                this.optionsArray.get(i).get(j).setColor(originColor);
                counter++;
                flag = false;
                //the cell is with negColor, so continue scanning.
            } else {
                //נניח הצבע ההתחלתי היה שחור, זה חיפש עכשיו לבנים ופגש עוד לבן, אז פועלים באופן רקורסיבי
                directionScan(i,j, changeRow,changeColumn,originColor,negColor);
            }
        }
    }

    private void directionUpside(int i, int j, int changeRow, int changeColumn, Color originColor, Color negColor) {
        int x = i, y = j;
        if(x < 0 || x >= this.board.getRowsNum() || y < 0 || y >= this.board.getColumnsNum()) {
            this.initializeOpt();
            return;
        }
        //check if the next cell is relevant(negative color).
        Cell cell = this.board.boardArr.get(i).get(j);
        if(cell.getColor() == negColor) {
            //add the cell to the option array(relevant cell).
            this.optionsArray.get(i).get(j).setColor(originColor);
            x += changeRow;
            y += changeColumn;
            this.directionUpside(x, y, changeRow, changeColumn, originColor, negColor);
            //find the originalColor cell, so we can upside down the option array.
        } else if(cell.getColor() == originColor){
            for(int a = 0; a < this.board.getRowsNum(); a++) {
                for(int b = 0; b < this.board.getColumnsNum(); b++) {
                    Cell c = this.optionsArray.get(a).get(b);
                    if(c.getColor() != Color.EMPTY) {
                        this.upSideDown(a, b, originColor);
                    }
                }
            }
            //the cell is irrelevant.
        } else {
            this.initializeOpt();
            return;
        }
        this.initializeOpt();
    }

    private void upSideDown(int x, int y, Color color) {
        Cell cell = this.board.boardArr.get(x).get(y);
        if(cell.getColor() == Color.EMPTY) {
            if(this.player1.getType() == color) {
                this.player1.increase();
            } else {
                this.player2.increase();
            }
        } else {
            if(this.player1.getType() == color) {
                this.player1.increase();
                this.player2.decrease();
            } else {
                this.player2.increase();
                this.player1.decrease();
            }
        }
        cell.setColor(color);
    }

    public boolean validOpt(int x, int y) {
        //check if the indexs are legal and if the cell is truly a valid option.
        if(x >= 0 && x < this.board.getRowsNum()
                && y >= 0 && y < this.board.getColumnsNum()) {
            Cell cell = this.optionsArray.get(x).get(y);
            if(cell.getColor() != Color.EMPTY) {
                return true;
            }
        }
        return false;
    }

    public void swap(int row, int column, Color originColor, Color negColor) {
        Action action = Action.FLIP;
        scanOpt(row, column, originColor, negColor, action);
    }

    public void printPoints() {
        int bPoints = this.player1.getCount();
        int wPoints = this.player2.getCount();
        System.out.println("Black player got " + bPoints + " points");
        System.out.println("White player got " + wPoints + " points");
        if(bPoints > wPoints) {
            System.out.print("the winner is: the Black player");
        } else if(bPoints < wPoints) {
            System.out.print("the winner is: the White player");
        } else {
            System.out.print("Its a draw");
        }
        this.initializeOpt();
    }

    @Override
    public Color getWinner() {
        if(player1.getCount() > player2.getCount()) {
            return player1.getType();
        }
        else if(player1.getCount() < player2.getCount()) {
            return player2.getType();
        }
        //if they got same score
        else {
            return Color.EMPTY;
        }

    }
}
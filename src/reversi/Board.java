package reversi;

import java.util.ArrayList;
import java.util.List;
/**
 * Board.
 */
public class Board {
    private int rowsNum;
    private int columnsNum;
    public List<List<Cell>> boardArr = new ArrayList<>();
    /**
     * Board.
     *
     * constructor.
     *
     * @param columns   the columns number.
     * @param rows      the rows number.
     */
    public Board(int rows, int columns) {
        this.rowsNum = rows;
        this.columnsNum = columns;

        //fill the matrix with space char.
        for (int row = 0; row < this.rowsNum; row ++ ) {
            List<Cell> rowCell = new ArrayList<Cell>();
            for (int column = 0; column < this.columnsNum; column ++) {
                Cell cell= new Cell(row, column, Color.EMPTY);
                rowCell.add(cell);
            }
            this.boardArr.add(rowCell);
        }
        int midRow = (int)Math.ceil(this.boardArr.size()/2.0);
        int midCol = (int)Math.ceil(this.boardArr.get(0).size()/2.0);
        //set the first disks.
        this.boardArr.get(midRow - 1).get(midCol -1).setColor(Color.WHITE);
        this.boardArr.get(midRow).get(midCol).setColor(Color.WHITE);
        this.boardArr.get(midRow - 1).get(midCol).setColor(Color.BLACK);
        this.boardArr.get(midRow).get(midCol - 1).setColor(Color.BLACK);
    }
    /**
     * getRowsNum.
     *
     * @return      the number of rows.
     */
    public int getRowsNum() {
        return this.rowsNum;
    }

    /**
     * getColumnsNum.
     *
     * @return      the number of columns.
     */
    public int getColumnsNum() {
        return this.columnsNum;
    }

    /**
     * printBoard.
     *
     * print the board.
     */
    public void printBoard() {
        /*
        //print the first line.
        System.out.print(" | ");
        for(int i=1; i<9; i++) {
            System.out.print(i);
            System.out.print(" | ");
        }
        System.out.println();
        System.out.println("----------------------------------");

        //print the next ones lines.
        //the loop runs all over the array's rows.
        for(int row = 0; row < 8; row++)
        {
            System.out.print(row + 1);
            System.out.print("|");
            //the loop runs all over the array's columns.
            for(int column = 0; column<8; column++)
            {
                //check if the cell is empty or not.
                if(boardArr.get(row).get(column).getColor() == Color.EMPTY){
                    System.out.print("   |");
                } else {
                    if(boardArr.get(row).get(column).getColor() == Color.WHITE) {
                        System.out.print(" O |");
                    } else {
                        System.out.print(" X |");
                    }
                }
            }
            System.out.println();
            System.out.println("----------------------------------");
        }
        */
    }
}
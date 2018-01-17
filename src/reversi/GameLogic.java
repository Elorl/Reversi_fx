package reversi;

/**
 * GameLogic.
 */
public interface GameLogic {

    /**
     * checkOpt.
     *
     * runs all over the board and looking for a cell that belongs to the player.
     *
     * @param color     the player's color.
     */
    int checkOpt(Color color);

    /**
     * swap.
     *
     * runs all over flip the relevant disks.
     *
     * @param column        the number of rows in the board.
     * @param row           the number of columns in the board.
     * @param negColor      the rival color.
     * @param originColor   the player color.
     */
    void swap(int row, int column, Color originColor, Color negColor);

    /**
     * validOpt.
     *
     * check if this cell is valid option.
     *
     * @param x     the x cord of the option.
     * @param y     the y cord of the option
     */
    boolean validOpt(int x, int y);

    /**
     * printOpt.
     *
     * print the options to the console.
     *
     * @param count     the amount of options.
     */
    void printOpt(int count);

    /**
     * initializeOpt.
     *
     * initialize the Arraylist of options.
     */
    void initializeOpt();

    /**
     * printPoints.
     *
     * print points.
     */
    void printPoints();

    /**
     * getWinner.
     *
     * @return      the color of the winner.
     */
    Color getWinner();
}
package reversi;

/**
 * reversi.reversi.GameLogic.
 */
public interface GameLogic {

    int checkOpt(Color color);

    void swap(int row, int column, Color originColor, Color negColor);

    boolean validOpt(int x, int y);

    void printOpt(int count);

    void initializeOpt();

    void printPoints();
}
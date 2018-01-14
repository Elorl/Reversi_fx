package reversi;

/**
 * Player.
 */
public interface Player {
    int getCount();
    char getSymbol();
    void increase();
    void decrease();
    Color getType();
    int[] input();
}
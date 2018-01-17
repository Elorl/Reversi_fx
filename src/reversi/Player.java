package reversi;


/**
 * Player.
 */
public interface Player {

    /**
     * getCount.
     *
     * the function return the amount of disks the player has.
     */
    int getCount();

    /**
     * getSymbol.
     *
     * the function return the char that represent the player.
     */
    char getSymbol();

    /**
     * increase.
     *
     * the function increase the counter by one.
     */
    void increase();

    /**
     * decrease.
     *
     * the function decrease the counter by one.
     */
    void decrease();

    /**
     * getType.
     *
     * the function return the player's color.
     */
    Color getType();

    /**
     * Player.
     *
     * the function get from the console the choose from the user.
     */
    int[] input();
}
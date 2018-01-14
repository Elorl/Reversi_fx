package reversi;

/**
 * reversi.
 */
public class Reversi {
    /**
     * main.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        Player player1 = new ManualPlayer(Color.BLACK);
        Player player2 = new ManualPlayer(Color.WHITE);
        Board board = new Board(8,8);
        GameLogic logic = new ManualLogic(board, player1, player2);
        Game game = new Game(board, player1, player2, logic);
        game.run();
    }
}
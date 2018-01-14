package reversi;

import java.util.Scanner;
/**
 * Game.
 */
public class Game {
    private Board borad;
    private Player player1;
    private Player player2;
    GameLogic logic;

    public Game(Board board, Player player1, Player player2, GameLogic logic) {
        this.borad = board;
        this.player1 = player1;
        this.player2 = player2;
        this.logic = logic;
    }

    public void run() {
        Integer inputX = 0, inputY = 0;
        int counter;
        char dummy;
        boolean run = true, flag = false;
        Player turnPlayer = player1;
        Color neg = Color.WHITE;
        while(run){
            counter = 0;
            this.logic.initializeOpt();
            System.out.println("current board:\n");
            this.borad.printBoard();
            System.out.println(turnPlayer.getSymbol() + ": It's your move.");
            //check the options the player has.
            counter = logic.checkOpt(turnPlayer.getType());
            //check if there is a possible option.
            if(counter > 0) {
                flag = false;
                logic.printOpt(counter);
                int input[] = turnPlayer.input();
                inputX = input[0];
                inputY = input[1];
                //check if this is a valid input.
                if(!this.logic.validOpt(inputX,inputY)) {
                    do {
                        System.out.println("Please enter correct move row,col:  ");
                        Scanner sc = new Scanner(System.in).useDelimiter("\n");
                        String inputString = sc.next();
                        String splt[] = inputString.split(",");
                        inputX = Integer.parseInt(splt[0]);
                        inputY = Integer.parseInt(splt[1]);
                        inputX--;
                        inputY--;
                    } while(!(this.logic.validOpt(inputX, inputY)));
                }
                //now, start to upside down all the relevant disks.
                this.logic.initializeOpt();
                //upside down the relevant disks.
                this.logic.swap(inputX, inputY, turnPlayer.getType(), neg);
                //there is no possible option.
            } else {
                System.out.println("No possible moves. Play passes back to the other player.");
                //if flag = true, so this is the second player that has no possible moves.
                if(flag) {
                    System.out.println("No possible moves. Game over!");
                    run = false;
                } else {
                    flag = true;
                }
            }
            if(turnPlayer == player1) {
                turnPlayer = player2;
                neg =  Color.BLACK;
            } else {
                turnPlayer = player1;
                neg = Color.WHITE;
            }
        }
        this.logic.printPoints();
        this.logic.initializeOpt();
    }
}
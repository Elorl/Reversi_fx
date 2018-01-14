package reversi;

import java.util.Scanner;
/**
 * ManualPlayer.
 */
public class ManualPlayer implements Player {
    char symbol;
    Color type;
    Counter counter = new Counter();

    public ManualPlayer(Color color) {
        this.type = color;
        if(type == Color.BLACK) {
            this.symbol = 'X';
        } else {
            this.symbol = 'O';
        }
    }
    public Color getType() {
        return this.type;
    }

    public int getCount() {
        return this.counter.get();
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void increase() {
        this.counter.increase(1);
    }

    public void decrease() {
        this.counter.decrease(1);
    }

    public int[] input() {
        char dummy;
        int tempX = 0, tempY = 0;
        int inputs[] = new int[2];
        System.out.println("\n");
        System.out.println("Please enter your move row,col:  ");
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        String inputString = sc.next();
        String splt[] = inputString.split(",");
        inputs[0] = Integer.parseInt(splt[0]) - 1;
        inputs[1] = Integer.parseInt(splt[1]) - 1;
        return inputs;
    }
}
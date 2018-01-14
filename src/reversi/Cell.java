package reversi;

/**
 * reversi.reversi.Cell.
 */
public class Cell {
    private int xCord;
    private int yCord;
    private Color color;

    public Cell(int x, int y, Color c) {
        this.xCord = x;
        this.yCord = y;
        this.color = c;
    }
    public Cell() {
        this.xCord = 0;
        this.yCord = 0;
        this.color = Color.EMPTY;
    }

    public int getX() {
        return this.xCord;
    }

    public int getY() {
        return this.yCord;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setX(int x) {
        this.xCord = x;
    }

    public void setY(int y) {
        this.yCord = y;
    }

    public void printCell() {
        System.out.print('(');
        System.out.print(this.xCord + 1);
        System.out.print(',');
        System.out.print(this.yCord + 1);
        System.out.print(')');
    }
}
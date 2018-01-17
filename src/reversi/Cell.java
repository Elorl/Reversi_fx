package reversi;

/**
 * Cell.
 */
public class Cell {
    private int xCord;
    private int yCord;
    private Color color;

    /**
     * Cell.
     *
     * constructor.
     *
     * @param c     the cell's color.
     * @param x     the x cord of the cell.
     * @param y     the y cord of the cell.
     */
    public Cell(int x, int y, Color c) {
        this.xCord = x;
        this.yCord = y;
        this.color = c;
    }

    /**
     * Cell.
     *
     * constructor.
     */
    public Cell() {
        this.xCord = 0;
        this.yCord = 0;
        this.color = Color.EMPTY;
    }

    /**
     * getX.
     *
     * @return      the x value.
     */
    public int getX() {
        return this.xCord;
    }

    /**
     * getY.
     *
     * @return      the y value.
     */
    public int getY() {
        return this.yCord;
    }

    /**
     * getColor.
     *
     * @return      the color of the cell.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * setColor.
     *
     * set color to the cell.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * setX.
     *
     * set x cord to the cell
     */
    public void setX(int x) {
        this.xCord = x;
    }

    /**
     * setY.
     *
     * set y cord to the cell
     */
    public void setY(int y) {
        this.yCord = y;
    }

    /**
     * printCell.
     *
     * print the cell cords.
     */
    public void printCell() {
        System.out.print('(');
        System.out.print(this.xCord + 1);
        System.out.print(',');
        System.out.print(this.yCord + 1);
        System.out.print(')');
    }
}
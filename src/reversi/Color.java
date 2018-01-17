package reversi;

/**
 * Color.
 */
public enum Color {
    BLACK, WHITE, EMPTY;

    /**
     * toggle.
     *
     * check if the color is equal to black.
     */
    public Color toggle() {
        if (this.equals(BLACK))
            return WHITE;
        else
            return BLACK;
    }
}
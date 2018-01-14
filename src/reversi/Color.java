package reversi;

/**
 * Color.
 */
public enum Color {
    BLACK, WHITE, EMPTY;

    public Color toggle() {
        if (this.equals(BLACK))
            return WHITE;
        else
            return BLACK;
    }
}
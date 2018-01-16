package gui;

import reversi.Color;
import reversi.Player;

public interface GameInfoListener {
    /**
     * updates info visually, after a change occurred.
     */
    void updateInfo();

    /**
     * alerting end of game for relevant game components.
     */
    void alertEnd(Color winnerColor);
}

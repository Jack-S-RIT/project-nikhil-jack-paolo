/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 */

package minesweeper.model;

public enum GameState {

    /**
     * Enumerations for the gamestate
     */
    NOT_STARTED("NOT STARTED"),
    IN_PROGRESS("IN PROGRESS"),
    WON("WON"),
    LOST("Lost");

    private String gameState;

    /**
     * Constructor for the Enum
     * 
     * @param state the state game is in
     */
    private GameState(String state) {
        this.gameState = state;
    }

    // Special Method
    @Override
    public String toString() {
        return gameState;
    }

}

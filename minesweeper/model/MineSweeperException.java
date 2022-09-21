/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 */

package minesweeper.model;

/**
 * A checked exception that may be thrown by a Minesweeper game.
 */
public class MineSweeperException extends Exception {
    /**
     * Creates a new Minesweeper exception
     * 
     * @param message The message describing the error that caused the
     *                exception.
     */
    public MineSweeperException(String message) {
        super(message);
    }

}

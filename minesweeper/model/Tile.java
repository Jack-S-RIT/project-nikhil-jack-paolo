/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 */

package minesweeper.model;

public class Tile extends Cell {

    // Intialize Fields
    private int value;

    /**
     * Tile constructor
     * 
     * @param location the location of the tile on the board
     */
    public Tile(Location location) {
        super(location);
    }

    // Getter
    public int getValue() {
        return this.value;
    }

    @Override
    public char getSymbol() {
        return toString().charAt(0);
    }

    // Mutator
    public void setValue(int value) {
        this.value = value;
    }

    // Special Method
    public String toString() {
        if (this.isCovered()) {
            return "-";
        } else if (value == 0) {
            return " ";
        } else {
            return String.valueOf(value);
        }

    }
}

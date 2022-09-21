/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 */

package minesweeper.model;

public class Mine extends Cell {

    /**
     * Constructor for mine
     * 
     * @param location the location the mine will be
     */
    public Mine(Location location) {
        super(location);
    }

    @Override
    public char getSymbol() {
        return toString().charAt(0);
    }

    // Special Method
    public String toString() {
        if (this.isCovered()) {
            return "-";
        } else {
            return "M";
        }
    }

}

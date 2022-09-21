/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 */

package minesweeper.model;

public class Location implements Comparable<Location> {
    // Intialize Fields
    private int row;
    private int col;

    /**
     * Constructor for a location
     * 
     * @param row the row the location is
     * @param col the column the location is
     */
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Getters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Special Methods
    @Override
    public int compareTo(Location o) {
        if (this.row == o.row) {
            return this.col - o.col;
        } else {
            return this.row - o.row;
        }
    }

    @Override
    public int hashCode() {
        return (int) Math.pow(row, col);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        Location o = (Location) obj;
        return this.row == o.row && o.col == this.col;
    }

    //

    public String toString() {
        return "{" + row + ", " + col + "}";
    }

}

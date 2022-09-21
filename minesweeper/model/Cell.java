package minesweeper.model;

/** Abstract Class for the Tiles and Mines. */
public abstract class Cell {

    /** Private Fields */
    private final Location location;
    private boolean isCovered;

    /**
     * The Cell constructor
     * 
     * @param location the location of the cell on the board
     */
    public Cell(Location location) {
        this.location = location;
        this.isCovered = true;
    }

    // Getters
    public Location getLocation() {
        return location;
    }

    /**
     * Returns if a cell is covered or not
     * 
     * @return T/F is the Cell is covered or not
     */
    public boolean isCovered() {
        return isCovered;
    }

    /**
     * Flips the cell on the board revealing its state
     */
    public void flip() {
        this.isCovered = isCovered ? false : isCovered;
    }

    public abstract char getSymbol();

}

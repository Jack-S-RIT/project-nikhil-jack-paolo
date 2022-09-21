/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 */

package minesweeper.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Board {

    private Cell[][] board;
    private final int ROWS;
    private final int COLS;
    private final Set<Location> MINE_CORDS;
    private final int totalTiles;
    private int tilesFlipped;
    private GameState gameState;

    public Board(int rows, int cols, int mineCount) {
        ROWS = rows - 1;
        COLS = cols - 1;
        MINE_CORDS = makeMines(mineCount);
        gameState = GameState.NOT_STARTED;
        board = makeBoard(rows, cols);
        totalTiles = (rows * cols) - mineCount;
        tilesFlipped = 0;
    }

    // Accessors
    public Cell[][] getBoard() {
        return board;
    }

    public Set<Location> getMineCords() {
        return MINE_CORDS;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public int getTilesFlipped() {
        return this.tilesFlipped;
    }

    public Cell getCell(Location location) {
        Cell cell = board[location.getRow()][location.getCol()];
        return cell;
    }

    // Mutators

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    // Helper Functions

    /**
     * This is a set of mines on the board. It makes and intiailzes the mines.
     * 
     * @return a set of mines on the board.
     */
    private Set<Location> makeMines(int mineCount) {
        Random RNG = new Random();
        Set<Location> cords = new TreeSet<>();
        while (cords.size() < mineCount) {
            int row = RNG.nextInt(ROWS + 1);
            int col = RNG.nextInt(COLS + 1);
            Location location = new Location(row, col);
            cords.add(location);
        }
        return cords;
    }

    /**
     * This is a function that makes the board. It makes both mines and tiles. It
     * uses the set from
     * makeMines() to make the board.
     * 
     * @return a 2D Cell array of the board with tiles and mines
     */
    public Cell[][] makeBoard(int rows, int cols) {
        Cell[][] cellArr = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Location location = new Location(row, col);
                if (MINE_CORDS.contains(location)) {
                    cellArr[row][col] = new Mine(location);
                } else {
                    cellArr[row][col] = new Tile(location);
                }
            }
        }
        return cellArr;
    }

    /**
     * This searches for the amount of mines surrounding the top left.
     * 
     * @return the amount of mines surrounding the tile
     */
    public Location[] checkTopLeft() {
        Location[] checks = new Location[] {
                this.board[0][1].getLocation(),
                this.board[1][0].getLocation(),
                this.board[1][1].getLocation()
        };
        return checks;
    }

    /**
     * This searches for the amount of mines surrounding the top right.
     * 
     * @return the amount of mines surrounding the tile
     */
    public Location[] checkTopRight() {
        Location[] checks = new Location[] {
                this.board[0][COLS - 1].getLocation(),
                this.board[1][COLS - 1].getLocation(),
                this.board[1][COLS].getLocation()
        };
        return checks;
    }

    /**
     * This searches for the amount of mines surrounding the bottom left.
     * 
     * @return the amount of mines surrounding the tile
     */
    public Location[] checkBottomLeft() {
        Location[] checks = new Location[] {
                this.board[ROWS][1].getLocation(),
                this.board[ROWS - 1][0].getLocation(),
                this.board[ROWS - 1][1].getLocation()
        };

        return checks;
    }

    /**
     * This searches for the amount of mines surrounding the bottom right.
     * 
     * @return the amount of mines surrounding the tile
     */
    public Location[] checkBottomRight() {
        Location[] checks = new Location[] {
                this.board[ROWS][COLS - 1].getLocation(),
                this.board[ROWS - 1][COLS].getLocation(),
                this.board[ROWS - 1][COLS - 1].getLocation()
        };
        return checks;
    }

    /**
     * Checks the tiles on the right edge and how many mines are surrounding it
     * 
     * @param row the row the edge is on
     * @return the amount of mines surrounding the tile
     */
    public Location[] checkTopEdge(int col) {
        Location[] checks = new Location[] {
                this.board[0][col - 1].getLocation(),
                this.board[0][col + 1].getLocation(),
                this.board[1][col - 1].getLocation(),
                this.board[1][col].getLocation(),
                this.board[1][col + 1].getLocation(),
        };
        return checks;
    }

    /**
     * Checks tiles on the left edge to know many mines are around it.
     * 
     * @param row the row the edge is on, column will always be zero
     * @return the amount of mines surrounding the tile
     */
    public Location[] checkLeftEdge(int row) {
        Location[] checks = new Location[] {
                this.board[row + 1][0].getLocation(),
                this.board[row + 1][1].getLocation(),
                this.board[row - 1][0].getLocation(),
                this.board[row - 1][1].getLocation(),
                this.board[row][1].getLocation(),
        };
        return checks;
    }

    /**
     * Checks tiles on the top edge to know how many mines are around it
     * 
     * @param col requires the column to know where it is on the board
     * @return the amount of mines around the tile
     */
    public Location[] checkRightEdge(int row) {
        Location[] checks = new Location[] {
                this.board[row - 1][COLS].getLocation(),
                this.board[row - 1][COLS - 1].getLocation(),
                this.board[row][COLS - 1].getLocation(),
                this.board[row + 1][COLS - 1].getLocation(),
                this.board[row + 1][COLS].getLocation(),
        };
        return checks;
    }

    /**
     * Checks tiles on the bottom edge to see how many mines surrounding it.
     * 
     * @param col the column the tile in
     * @return the amount of mines surrounding the tile
     */
    public Location[] checkBottomEdge(int col) {
        Location[] checks = new Location[] {
                this.board[ROWS][col - 1].getLocation(),
                this.board[ROWS][col + 1].getLocation(),
                this.board[ROWS - 1][col - 1].getLocation(),
                this.board[ROWS - 1][col].getLocation(),
                this.board[ROWS - 1][col + 1].getLocation(),
        };
        return checks;
    }

    public Location[] checkMiddle(int row, int col) {
        Location[] checks = new Location[] {
                this.board[row - 1][col - 1].getLocation(),
                this.board[row - 1][col].getLocation(),
                this.board[row - 1][col + 1].getLocation(),
                this.board[row][col - 1].getLocation(),
                this.board[row][col + 1].getLocation(),
                this.board[row + 1][col - 1].getLocation(),
                this.board[row + 1][col].getLocation(),
                this.board[row + 1][col + 1].getLocation() };
        return checks;
    }

    /**
     * Evaluatres tiles based on where they are on the board specifically.
     * Needs the row and col to work, and also includes cases for all types of tiles
     * 
     * @param row the row the tile in
     * @param col the col the tile in
     */
    public Location[] evaluateTile(int row, int col) {
        Location[] checks;
        if (row == 0 && col == 0) {
            checks = checkTopLeft();
        } else if (row == 0 && col == COLS) {
            checks = checkTopRight();
        } else if (row == ROWS && col == 0) {
            checks = checkBottomLeft();
        } else if (row == ROWS && col == COLS) {
            checks = checkBottomRight();
        } else if (row == 0) {
            checks = checkTopEdge(col);
        } else if (col == 0) {
            checks = checkLeftEdge(row);
        } else if (col == COLS) {
            checks = checkRightEdge(row);
        } else if (row == ROWS) {
            checks = checkBottomEdge(col);
        } else {
            checks = checkMiddle(row, col);
        }
        return checks;
    }

    /**
     * Determines the value of a cell on the board
     * 
     * @param checks an array of locations to be checked
     * @return the value of that tile
     */
    public int determineValue(Location[] checks) {
        int mineCount = 0;
        for (int i = 0; i < checks.length; i++) {
            if (MINE_CORDS.contains(checks[i])) {
                mineCount++;
            }
        }
        return mineCount;
    }

    /**
     * Recursively reveals all the surrounding neighbors that also have a value of
     * 0.
     * 
     * @param tile    the tile it will start with
     * @param visited the tiles that have been visited
     */
    public void uncoverTiles(Tile tile, Set<Location> visited) {
        Set<Location> neighbors = new HashSet<>();

        Location[] locations = evaluateTile(tile.getLocation().getRow(), tile.getLocation().getCol());
        for (Location location : locations) {
            neighbors.add(location);
        }
        for (Location neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                selectEmpty(neighbor, visited);
            }
        }
    }

    // Special Method
    public String toString() {
        String s = "";
        for (int row = 0; row < ROWS + 1; row++) {
            s += "\n";
            for (int col = 0; col < COLS + 1; col++) {
                s += "[" + board[row][col] + "]";
            }
        }
        return s;
    }

    /**
     * Selects a location on the board and evaluates it.
     * 
     * @param location the location of the cell
     * @return a cell that now has a value
     */
    public Cell select(Location location) {
        int row = location.getRow();
        int col = location.getCol();
        Cell cell = board[0][0];
        try {
            cell = board[row][col];
            cell.flip();
            if (!(MINE_CORDS.contains(cell.getLocation()))) {
                tilesFlipped++;
                Location[] checks = evaluateTile(row, col);
                int mineCount = determineValue(checks);
                Tile tile = (Tile) cell;
                tile.setValue(mineCount);
                if (tile.getValue() == 0) {
                    Set<Location> visited = new HashSet<>();
                    uncoverTiles(tile, visited);
                }
                setGameState(GameState.IN_PROGRESS);
                if (tilesFlipped == totalTiles) { // fix tile count and win
                    setGameState(GameState.WON);
                    //System.out.println("Congratulations!");
                }
            } else {
                setGameState(GameState.LOST);
                //System.out.println("BOOM! Better luck next time!");
            }
            board[row][col] = cell;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid move");
        }
        return cell;
    }

    /**
     * 
     * @param location
     * @param visited
     * @return
     */
    public Cell selectEmpty(Location location, Set<Location> visited) {
        int row = location.getRow();
        int col = location.getCol();
        Cell cell = board[0][0];
        try {
            cell = board[row][col];
            cell.flip();
            if (!(MINE_CORDS.contains(cell.getLocation()))) {
                tilesFlipped++;
                Location[] checks = evaluateTile(row, col);
                int mineCount = determineValue(checks);
                Tile tile = (Tile) cell;
                tile.setValue(mineCount);
                if (tile.getValue() == 0) {
                    uncoverTiles(tile, visited);
                }
                setGameState(GameState.IN_PROGRESS);
            }
            board[row][col] = cell;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid move");
        }
        return cell;
    }

    /**
     * Reveals the board and shows where the mines/other values are on the board
     */
    public void revealBoard() {
        for (int row = 0; row <= ROWS; row++) {
            for (int col = 0; col <= COLS; col++) {
                Cell cell = board[row][col];
                cell.flip();
                if (!(MINE_CORDS.contains(cell.getLocation()))) {
                    Location[] checks = evaluateTile(row, col);
                    int mineCount = determineValue(checks);
                    Tile tile = (Tile) cell;
                    tile.setValue(mineCount);
                }
                board[row][col] = cell;
            }
        }
    }

}

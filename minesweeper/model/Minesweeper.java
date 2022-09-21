/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 */

package minesweeper.model;

import java.util.Set;
import java.util.TreeSet;

public class Minesweeper {

    // Intialize The field
    public final static char MINE = 'M';

    public final static char COVERED = '-';

    private int moveCount;
    private final int MINE_COUNT;

    private final int ROWS;
    private final int COLS;

    private Board board;

    private boolean isFlag;

    /** Observer Intialize */
    private MinesweeperObserver observer;

    /**
     * 
     * @param rows      number of rows in the game
     * @param cols      number of columns in the game
     * @param mineCount the number of mines in the game
     */
    public Minesweeper(int rows, int cols, int mineCount) {
        this.ROWS = rows;
        this.COLS = cols;
        this.MINE_COUNT = mineCount;

        this.board = new Board(rows, cols, mineCount);

        this.moveCount = 0;
        this.isFlag = false;
    }

    public Minesweeper(Minesweeper minesweeper) {
        this.moveCount = minesweeper.getMoveCount();
        this.MINE_COUNT = minesweeper.getMineCount();
        this.ROWS = minesweeper.getRows();
        this.COLS = minesweeper.getCols();
        this.board = minesweeper.getBoard();
        this.isFlag = minesweeper.getIsFlag();

    }

    public Minesweeper deepCopy() {
        return new Minesweeper(this);
    }

    /**
     * Registers the observer
     * 
     * @param observer the observer
     */
    public void register(MinesweeperObserver observer) {
        this.observer = observer;
    }

    private final void notifyObserver(Location location) {
        if (this.observer != null) {
            observer.cellUpdated(location);
        }
    }

    public char getSymbol(Location location) {
        Cell cell = board.getCell(location);
        return cell.getSymbol();
    }

    public Cell getCell(Location location) {
        Cell cell = board.getCell(location);
        return cell;
    }

    public boolean isCovered(Location location) {
        Cell cell = board.getCell(location);
        return cell.isCovered();
    }

    // Getters
    public int getMoveCount() {
        return this.moveCount;
    }

    public int getMineCount() {
        return this.MINE_COUNT;
    }

    public GameState getGameState() {
        return board.getGameState();
    }

    public boolean getIsFlag() {
        return this.isFlag;
    }

    public void flipIsFlag() {
        this.isFlag = !isFlag;
    }

    public void flagTile(Location location) {
        notifyObserver(location);
    }

    /**
     * Gets a set of a possible selections by the user. It used for giving the
     * player hints
     * 
     * @return a set of locations
     */
    public Set<Location> getPossibleSelection() {

        Set<Location> validMoves = new TreeSet<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Location location = new Location(i, j);
                if (!board.getMineCords().contains(location)) {
                    validMoves.add(location);
                }
            }

        }
        return validMoves;
    }

    /**
     * Checks the state of the game
     * 
     * @return T/F to see if the game is still on going or not
     */
    public boolean checkGameState() {
        return board.getGameState() != GameState.LOST && board.getGameState() != GameState.WON;
    }

    /**
     * Makes a play and increment moveCount
     * 
     * @param location selects a location on the board
     */
    public Cell makePlay(Location location) {
        Cell cell = board.select(location);
        notifyObserver(location);
        return cell;
    }

    // Getter
    public Board getBoard() {
        return this.board;
    }

    // Setter
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Reveals the board as to where the mines are
     */
    public void revealBoard() {
        board.revealBoard();
    }

    public int getCols() {
        return COLS;
    }

    public int getRows() {
        return ROWS;
    }

    // Special Method
    @Override
    public String toString() {
        String s = "";
        for (int row = 0; row < ROWS; row++) {
            s += "\n";
            for (int col = 0; col < COLS; col++) {
                s += "[" + board.getBoard()[row][col] + "]";
            }
        }
        return s;
    }

    /**
     * resets the board and notifys the observer
     */
    public void resetBoard() {
        Board reset = new Board(ROWS, COLS, MINE_COUNT);
        setBoard(reset);
        moveCount = 0;
        notifyObserver(null);
    }

    public void incrementMoveCount() {
        this.moveCount++;
    }

    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(10, 10, 10);
        Board board = game.getBoard();
        System.out.println(board.getMineCords());
        System.out.println(game.getPossibleSelection());
        System.out.println(game);

    }

}

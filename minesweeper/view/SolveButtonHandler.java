package minesweeper.view;

import backtracker.Backtracker;
import backtracker.Configuration;
/**
 * @author Jackson Shortell
 * @author Paolo Pop
 * @author Nikhil Patil
 * Project: Minesweeper part 2
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import minesweeper.MinesweeperSolver;
import minesweeper.model.Cell;
import minesweeper.model.GameState;
import minesweeper.model.Location;
import minesweeper.model.Mine;
import minesweeper.model.Minesweeper;
import minesweeper.model.Tile;

public class SolveButtonHandler implements EventHandler<ActionEvent> {

    // Intialize Fields
    private Minesweeper minesweeper;
    private MinesweeperGUI gui;

    // Constructor
    public SolveButtonHandler(Minesweeper minesweeper, MinesweeperGUI gui) {
        this.minesweeper = minesweeper;
        this.gui = gui;
    }

    /**
     * calls reset board on the given model of the game
     */
    @Override
    public void handle(ActionEvent arg0) {
        if (minesweeper.getGameState() != GameState.LOST) {
            MinesweeperSolver solver = gui.getSolver();
            Backtracker backtracker = new Backtracker(false);
            Configuration solution = backtracker.solve(solver);
            MinesweeperSolver minesweeperSolver = (MinesweeperSolver) solution;
            minesweeper = minesweeperSolver.getGame();
            int score = (MinesweeperGUI.ROWS * MinesweeperGUI.COLS) - MinesweeperGUI.MINES;
            gui.setMoveCount(score);

            Button startButton = gui.getStartButton();

            startButton.setGraphic(new ImageView(MinesweeperGUI.WIN_FACE)); // if the mine is clicked you lose
            for (int row = 0; row < MinesweeperGUI.ROWS; row++) {
                for (int col = 0; col < MinesweeperGUI.COLS; col++) { // loops through the entire board
                    Location location = new Location(row, col);
                    Button button = gui.getGridPaneChild(location);

                    Cell cell = minesweeper.getCell(location);
                    if (cell instanceof Mine) {
                        button.setDisable(true);
                        button.setGraphic(new ImageView(MinesweeperGUI.MINE)); // flips every mine
                    } else {
                        char s = ((Tile) cell).getSymbol();
                        Label label = MinesweeperGUI.makeLabel(s);
                        button.setGraphic(label); // flips every tile
                    }

                }
            }
        } else {
            
        }

    }
}

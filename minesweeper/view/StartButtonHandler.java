package minesweeper.view;

/**
 * @author Jackson Shortell
 * @author Paolo Pop
 * @author Nikhil Patil
 * Project: Minesweeper part 2
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import minesweeper.model.Minesweeper;

public class StartButtonHandler implements EventHandler<ActionEvent> {

    // Intialize Fields
    private Minesweeper minesweeper;

    // Constructor
    public StartButtonHandler(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
    }

    /**
     * calls reset board on the given model of the game
     */
    @Override
    public void handle(ActionEvent arg0) {
        minesweeper.resetBoard();
        if (minesweeper.getIsFlag()) {
            minesweeper.flipIsFlag();
        }
    }

}

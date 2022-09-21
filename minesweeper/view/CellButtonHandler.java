package minesweeper.view;
/**
 * @author Jackson Shortell
 * @author Paolo Pop
 * @author Nikhil Patil
 * Project: Minesweeper part 2
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import minesweeper.model.Cell;
import minesweeper.model.Location;
import minesweeper.model.Mine;
import minesweeper.model.Minesweeper;

public class CellButtonHandler implements EventHandler<ActionEvent> {

    //Intialize Fields
    private Minesweeper minesweeper;
    private Button button;
    private Location location;

    /**
     * Constructor for the Cell Button Handler
     * @param button 
     * @param location
     * @param minesweeper
     */
    public CellButtonHandler(Button button, Location location, Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
        this.button = button;
        this.location = location;
    }

    //Accessor
    public Button getButton() {
        return button;
    }

    
    @Override
    public void handle(ActionEvent arg0) {

        Boolean flag = minesweeper.getIsFlag();
        

        if (flag == false) {
            Cell cell = minesweeper.getCell(location); // gets the clicked cell

            if (cell.isCovered()) {

                if (cell instanceof Mine) { // if its a mine reveal the board and click the mine
                    minesweeper.revealBoard();
                    System.out.println(minesweeper.getGameState());
                } else {
                    // other wise increase moves made by 1 and play that tile
                    minesweeper.incrementMoveCount();
                }
                minesweeper.makePlay(location);
                System.out.println(minesweeper.getGameState());
            }
        } else {
            minesweeper.flagTile(location);
        }

    }

}

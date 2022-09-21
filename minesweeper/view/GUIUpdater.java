package minesweeper.view;

/**
 * @author Jackson Shortell
 * @author Paolo Pop
 * @author Nikhil Patil
 * Project: Minesweeper part 2
 */
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import minesweeper.model.Cell;
import minesweeper.model.GameState;
import minesweeper.model.Location;
import minesweeper.model.Mine;
import minesweeper.model.Minesweeper;
import minesweeper.model.MinesweeperObserver;
import minesweeper.model.Tile;

public class GUIUpdater implements MinesweeperObserver {

    // Intialize Fields
    private MinesweeperGUI gui;

    // Constructor
    public GUIUpdater(MinesweeperGUI gui) {
        this.gui = gui;
    }

    /**
     * Cell Updated, Modifies on the GUI, Observes the model
     */
    @Override
    public void cellUpdated(Location location) {
        Minesweeper minesweeper = gui.getMinesweeper();
        boolean isFlag = minesweeper.getIsFlag();
        Button flag = gui.getFlag();
        Button startButton = gui.getStartButton();
        //System.out.println(minesweeper.getGameState());
        GridPane gridPane = gui.getGridPane();

        

        if (location == null) { // meaning the start button was called as it has no location
            flag.setGraphic(MinesweeperGUI.makeLabel("Flag", Color.BLACK, Color.LIGHTGRAY));
            startButton.setGraphic(new ImageView(MinesweeperGUI.WIN_FACE)); // when you click it turns to win
            gui.setMoveCount(gui.getMinesweeper().getMoveCount()); // resets the move count
            for (Node node : gridPane.getChildren()) {
                Button button = (Button) node;
                button.setGraphic(new ImageView(MinesweeperGUI.CELL)); // changes every tile back to covered
                button.setText(null);
            }
            gui.enableBoard();
        }

        else if (isFlag == true) {
            flag.setGraphic(MinesweeperGUI.makeLabel("Flag", Color.RED, Color.LIGHTGRAY));

            Button button = gui.getGridPaneChild(location);
            Cell cell = gui.getMinesweeper().getCell(location);
            if (cell.isCovered()) {
                button.setGraphic(new ImageView(MinesweeperGUI.FLAG));

            }
        }

        else if (isFlag == false) {
            Cell cell = gui.getMinesweeper().getCell(location);

            char symbol = cell.getSymbol();

            if (symbol == Minesweeper.MINE) { // when a mine is clicked
                startButton.setGraphic(new ImageView(MinesweeperGUI.LOSE_FACE)); // if the mine is clicked you lose
                for (int row = 0; row < MinesweeperGUI.ROWS; row++) {
                    for (int col = 0; col < MinesweeperGUI.COLS; col++) { // loops through the entire board
                        Location location2 = new Location(row, col);
                        Button button2 = gui.getGridPaneChild(location2);
                        Cell cell2 = minesweeper.getCell(location2);
                        if (cell2 instanceof Mine) {
                            button2.setGraphic(new ImageView(MinesweeperGUI.MINE)); // flips every mine
                        } else {
                            char s = ((Tile) cell2).getSymbol();
                            Label label = MinesweeperGUI.makeLabel(s);
                            button2.setGraphic(label); // flips every tile
                        }

                    }
                }

            } else if (symbol == ' ') { // if a zero is clicked flips it and flips its cleared neighbors
                startButton.setGraphic(new ImageView(MinesweeperGUI.IN_PROGRESS_FACE));
                if(minesweeper.getGameState() == GameState.WON){
                    startButton.setGraphic(new ImageView(MinesweeperGUI.WIN_FACE));
                }
                for (int row = 0; row < MinesweeperGUI.ROWS; row++) {
                    for (int col = 0; col < MinesweeperGUI.COLS; col++) {
                        Location location2 = new Location(row, col);
                        Cell cell2 = minesweeper.getCell(location2);
                        if (!cell2.isCovered()) {
                            Button button2 = gui.getGridPaneChild(location2);
                            char s = ((Tile) cell2).getSymbol();
                            Label label = MinesweeperGUI.makeLabel(s);
                            button2.setGraphic(label);
                            gui.setMoveCount(minesweeper.getMoveCount());
                        }
                    }
                }
            } else { // tile cell with value greater than 0;
                startButton.setGraphic(new ImageView(MinesweeperGUI.IN_PROGRESS_FACE));
                Button button = gui.getGridPaneChild(location);
                Label label = MinesweeperGUI.makeLabel(symbol);
                button.setGraphic(label);
                gui.setMoveCount(gui.getMinesweeper().getMoveCount());
                if(minesweeper.getGameState() == GameState.WON){
                    startButton.setGraphic(new ImageView(MinesweeperGUI.WIN_FACE));
                }
            }
        }
        

    }

}

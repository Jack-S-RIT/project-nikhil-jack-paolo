package minesweeper.view;

/**
 * @author Jackson Shortell
 * @author Paolo Pop
 * @author Nikhil Patil
 * Project: Minesweeper part 2
 */
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import minesweeper.MinesweeperSolver;
import minesweeper.model.GameState;
import minesweeper.model.Location;
import minesweeper.model.Minesweeper;

public class MinesweeperGUI extends Application {

    protected final static int ROWS = 10;
    protected final static int COLS = 10;
    protected final static int MINES = 15;
    public final static Image CELL = new Image("media/images/cell.png");
    public final static Image MINE = new Image("media/images/mine24.png");
    public final static Image LOSE_FACE = new Image("media/images/lose_face.png");
    public final static Image WIN_FACE = new Image("media/images/start_win_face.png");
    public final static Image IN_PROGRESS_FACE = new Image("media/images/inProgress_face.png");
    public final static Image FLAG = new Image("media/images/flag24.png");
    public final static ImageView CELLVIEW = new ImageView("media/images/cell.png");
    public final static ImageView FLAGVIEW = new ImageView("media/images/flag24.png");

    private Label moveCount;
    private Minesweeper minesweeper;
    private GridPane gridPane;
    private Button startButton;
    private Button hint;
    private Button flag;
    private MinesweeperSolver solver;

    /**
     * creates label with certain color text depending on the char
     * 
     * @param c char
     * @return returns a label
     */
    public static Label makeLabel(char c) {
        String s = c + "";
        Color foreground = getColor(c);
        Label label = new Label(s);
        Font font = new Font(16);
        Font.font("Roboto", FontWeight.EXTRA_BOLD, 24);
        label.setFont(font);
        // label.setPrefWidth(Double.POSITIVE_INFINITY);
        label.setPadding(new Insets(0));
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setMaxHeight(Double.MAX_VALUE);
        label.setTextFill(foreground);
        label.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        return label;

    }

    /**
     * Factory Method to make a label
     * 
     * @param text       of the label
     * @param foreground color of the text
     * @param background background color of the label
     * @return
     */
    public static Label makeLabel(String text, Color foreground, Color background) {
        Label label = new Label(text);
        label.setTextFill(foreground);
        label.setFont(new Font("Courier New", 16));
        label.setPadding(new Insets(5));
        label.setMaxHeight(Double.MAX_VALUE);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        return label;
    }

    /**
     * creates a button
     * 
     * @return a button
     */
    public Button makeButton() {
        Button button = new Button();
        button.setPadding(new Insets(2));
        button.setAlignment(Pos.CENTER);
        // button.setCenterShape(true);
        return button;
    }

    /**
     * creates the board for the minesweeper game
     */
    public GridPane makeBoard(Minesweeper game) {
        gridPane = new GridPane();
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Location location = new Location(col, row);
                Button button = makeButton();

                button.setOnAction(new CellButtonHandler(button, location, game));
                button.setPrefHeight(30);
                button.setPrefWidth(30);
                button.setGraphic(new ImageView(CELL));
                gridPane.add(button, row, col);
                // GridPane.setHgrow(button, Priority.ALWAYS);
            }
        }

        return gridPane;

    }

    /** Main for javaFx */
    @Override
    public void start(Stage stage) throws Exception {

        minesweeper = new Minesweeper(ROWS, COLS, MINES); // Intialize Game
        minesweeper.register(new GUIUpdater(this)); // Register GUIUpdater
        System.out.println(minesweeper.getGameState());
        solver = new MinesweeperSolver(minesweeper);

        Label mineCount = makeLabel("Mine count: " + MINES, Color.BLACK, Color.LIGHTGRAY); // Makes
        // mineCount.setPadding(new Insets(10));
        mineCount.setMaxWidth(Double.POSITIVE_INFINITY);

        moveCount = makeLabel("Move count: " + 0, Color.BLACK, Color.LIGHTGRAY); // Makes the moveCount Label
        moveCount.setMaxWidth(Double.POSITIVE_INFINITY);

        startButton = new Button(); // Makes the Start Button
        startButton.setMaxHeight(Double.POSITIVE_INFINITY);
        startButton.setMaxWidth(48);
        startButton.setPadding(new Insets(2));
        startButton.setGraphic(new ImageView(WIN_FACE));
        startButton.setOnAction(new StartButtonHandler(minesweeper));

        hint = makeButton();
        hint.setAlignment(Pos.CENTER);
        hint.setMaxWidth(Double.POSITIVE_INFINITY);
        hint.setMaxHeight(Double.POSITIVE_INFINITY);
        hint.setGraphic(makeLabel("Hint", Color.BLACK, Color.LIGHTGRAY));
        hint.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) { // Hint Button functionality
                if (minesweeper.getGameState() != GameState.LOST) {
                    Location l = getHint(minesweeper);
                    Button b = (Button) getGridPaneChild(l);
                    Label la = new Label();
                    la.setMaxHeight(50);
                    la.setMaxHeight(50);
                    la.setText("            ");
                    la.setTextFill(Color.TRANSPARENT);
                    la.setBackground(
                            new Background(new BackgroundFill(Color.LIMEGREEN, new CornerRadii(3), Insets.EMPTY)));

                    b.setGraphic(la);

                }

            }

        });
        flag = makeButton(); // Flags
        flag.setAlignment(Pos.CENTER);
        flag.setGraphic(makeLabel("Flag", Color.BLACK, Color.LIGHTGRAY)); // Sets the graphic to flag to notify the user
        flag.setMaxWidth(Double.POSITIVE_INFINITY);
        flag.setMaxHeight(Double.POSITIVE_INFINITY);
        flag.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) { // Flag Button functionality
                minesweeper.flipIsFlag();
                if (minesweeper.getIsFlag() == true) {
                    flag.setGraphic(MinesweeperGUI.makeLabel("Flag", Color.RED, Color.LIGHTGRAY));
                } else {
                    flag.setGraphic(MinesweeperGUI.makeLabel("Flag", Color.BLACK, Color.LIGHTGRAY));
                }
            }
        });

        Button solve = makeButton();
        solve.setAlignment(Pos.CENTER);
        solve.setGraphic(makeLabel("Solve", Color.BLACK, Color.LIGHTGRAY));
        solve.setMaxWidth(Double.POSITIVE_INFINITY);
        solve.setMaxHeight(Double.POSITIVE_INFINITY);
        solve.setOnAction(new SolveButtonHandler(minesweeper, this));

        GridPane board = makeBoard(minesweeper);
        board.setMaxHeight(Double.POSITIVE_INFINITY);

        HBox score = new HBox();
        // HBox.setHgrow(mineCount, Priority.ALWAYS);
        HBox.setHgrow(moveCount, Priority.ALWAYS);
        // HBox.setHgrow(startButton, Priority.ALWAYS);
        score.getChildren().addAll(mineCount, startButton, moveCount);

        VBox buttons = new VBox();
        VBox.setVgrow(flag, Priority.ALWAYS);
        VBox.setVgrow(hint, Priority.ALWAYS);
        VBox.setVgrow(solve, Priority.ALWAYS);
        buttons.getChildren().addAll(flag, hint, solve);

        HBox play = new HBox();
        play.getChildren().addAll(board, buttons);

        VBox finalBox = new VBox();
        finalBox.getChildren().addAll(score, play);

        stage.setScene(new Scene(finalBox));
        stage.setTitle("Minesweeper");
        stage.show();
    }

    // Getters and Setters
    public Label getMoveCount() {
        return this.moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount.setText("Move count: " + moveCount);
    }

    public Minesweeper getMinesweeper() {
        return minesweeper;
    }

    public MinesweeperSolver getSolver() {
        return solver;
    }

    public Button getFlag() {
        return flag;
    }

    public void setGame(Minesweeper minesweeper) {
        this.minesweeper = minesweeper;
    }

    public GridPane getGridPane() {
        return this.gridPane;
    }

    public Button getStartButton() {
        return this.startButton;
    }

    public void enableBoard() {
        for (int row = 0; row < MinesweeperGUI.ROWS; row++) {
            for (int col = 0; col < MinesweeperGUI.COLS; col++) { // loops through the entire board
                Location location = new Location(row, col);
                Button button = getGridPaneChild(location);
                button.setDisable(false);
            }
        }
    }

    /**
     * given a location gets the node at the location in a gridpane
     * 
     * @param location passes in a location
     * @return returns the button in the grid pane
     */
    public Button getGridPaneChild(Location location) {
        int row = location.getRow();
        int col = location.getCol();
        Button button = null;
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                button = (Button) node;
            }
        }
        return button;
    }

    public Location getHint(Minesweeper game) { // Gets hint from a game
        Random rng = new Random();
        Location location = new Location(rng.nextInt(ROWS), rng.nextInt(COLS));
        while (game.getBoard().getMineCords().contains(location) || !(game.getBoard().getCell(location).isCovered())) {
            location = new Location(rng.nextInt(ROWS), rng.nextInt(COLS));
        }
        return location;
    }

    /**
     * gets a specific color depending on the char
     * 
     * @param c passes in a char
     * @return returns a color
     */

    public static Color getColor(char c) {
        Color foreground;
        if (c == '1') {
            foreground = Color.BLUE;
        } else if (c == '2') {
            foreground = Color.GREEN;
        } else if (c == '3') {
            foreground = Color.RED;
        } else if (c == '4') {
            foreground = Color.MAGENTA;
        } else if (c == '5') {
            foreground = Color.DARKRED;
        } else if (c == '6') {
            foreground = Color.DARKTURQUOISE;
        } else if (c == '7') {
            foreground = Color.BLACK;
        } else if (c == '8') {
            foreground = Color.GRAY;
        } else {
            foreground = Color.TRANSPARENT;
        }
        return foreground;
    }

    public static void main(String[] args) {

        launch(args);
        System.out.println("");

    }

}

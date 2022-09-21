/**
 * @author Nikhil Patil
 * @author Paolo Pop
 * @author Jackson Shortell
 * GCIS Project 1
 * 
 */

package minesweeper.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import minesweeper.MinesweeperSolver;
import minesweeper.model.Location;
import minesweeper.model.Minesweeper;

public class MinesweeperCLI {

    // Intialize Fields

    private final static List<String> COMMANDS = new ArrayList<>();
    public final static int ROWS = 4;
    public final static int COLS = 4;
    public final static int MINE_COUNT = 2;

    // The Commands that are used in the game

    static {
        COMMANDS.add("\thelp - displays this message");
        COMMANDS.add("\tpick <row col> - uncovers a tile at this location");
        COMMANDS.add("\thint - displays an availiable move (i.e where a mine is not located)");
        COMMANDS.add("\treset - resets game, board, & score");
        COMMANDS.add("\tsolve - solves the game for you");
        COMMANDS.add("\tquit - quit/exit game");
    }

    /**
     * Prints the commands that will aid the user play the CLI game.
     */
    public static void printCommands() {
        System.out.println("Mines: " + MINE_COUNT);
        System.out.println("Commands: ");
        COMMANDS.stream().forEach(System.out::println);
    }

    /**
     * Gives the player a hint when they are stuck. Uses a set of every possible
     * move and gives the player a hint
     * 
     * @param game the instance of the game you will pass into it
     * @return a location that is random from a set from possible moves
     */
    public static Location getHint(Minesweeper game) {
        Random rng = new Random();
        Location location = new Location(rng.nextInt(ROWS), rng.nextInt(COLS));
        while (game.getBoard().getMineCords().contains(location) || !(game.getBoard().getCell(location).isCovered())) {
            location = new Location(rng.nextInt(ROWS), rng.nextInt(COLS));
        }
        return location;
    }

    /**
     * The static method to play the minesweeper game. It checks for user input and
     * allows the user to play the
     * MineSweeper CLI game.
     */
    public static void play() {
        Scanner scanner = new Scanner(System.in);
        Minesweeper game = new Minesweeper(ROWS, COLS, MINE_COUNT);
        while (game.checkGameState()) {
            try {
                System.out.println(game + "\n");
                System.out.println("Moves: " + game.getMoveCount() + "\n");
                System.out.print("Enter Command: ");
                String[] input = scanner.nextLine().split(" ");
                if (input[0].equals("help")) {
                    printCommands();
                } else if (input[0].equals("pick")) {
                    try {
                        Location location = new Location(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                        game.makePlay(location);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid move");
                    }
                } else if (input[0].equals("hint")) {
                    Location hint = getHint(game);
                    System.out.println("Give (" + hint.getRow() + ", " + hint.getCol() + ") a try");
                } else if (input[0].equals("reset")) {
                    System.out.println("Resetting to a new game.");
                    game = new Minesweeper(ROWS, COLS, MINE_COUNT);
                } else if (input[0].equals("quit")) {
                    scanner.close();
                    System.exit(1);
                } else if (input[0].equals("solve")) {
                    System.out.println(MinesweeperSolver.solve(game));
                }

                else {
                    System.out.println("Invalid Command");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid move");
            }
        }
        game.revealBoard();
        System.out.println("\nMoves: " + game.getMoveCount() + "\n");
    }

    // Main
    public static void main(String[] args) {
        printCommands();
        play();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Command (quit/reset): ");
        String input = scanner.nextLine();
        while (true) {
            if (input.equals("quit")) {
                scanner.close();
                System.exit(1);
            } else if (input.equals("reset")) {
                System.out.println("Resetting to a new game.");
                play();
            } else {
                System.out.println("Invalid Command");
                System.out.print("Enter Command (quit/reset): ");
                input = scanner.nextLine();
            }
            System.out.print("Enter Command (quit/reset):");
            input = scanner.nextLine();
        }
    }
}

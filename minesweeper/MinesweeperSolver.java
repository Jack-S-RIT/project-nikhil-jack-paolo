package minesweeper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backtracker.Backtracker;
import backtracker.Configuration;
import minesweeper.model.GameState;
import minesweeper.model.Location;
import minesweeper.model.Minesweeper;

public class MinesweeperSolver implements Configuration {

    private Minesweeper game;

    public MinesweeperSolver(Minesweeper game) {
        this.game = game;
    }

    @Override
    public Collection<Configuration> getSuccessors() {

        List<Configuration> successors = new ArrayList<>();

        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getCols(); col++) {
                Location loc = new Location(row, col);
                if (game.getBoard().getMineCords().contains(loc)) {
                    continue;
                } else if (game.getCell(loc).isCovered()) {
                    Minesweeper gameCopy = game.deepCopy();
                    gameCopy.makePlay(loc);
                    successors.add(new MinesweeperSolver(gameCopy));
                }
            }
        }
        return successors;
    }

    @Override
    public boolean isValid() {
        return game.getGameState() == GameState.IN_PROGRESS || game.getGameState() == GameState.WON;
    }

    @Override
    public boolean isGoal() {
        if (game.getGameState() == GameState.WON) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        try {
            return game.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String solve(Minesweeper game){
        Backtracker backtracker = new Backtracker(false);
        Configuration solution = backtracker.solve(new MinesweeperSolver(game));
        return solution.toString();
    }

    public Minesweeper getGame(){
        return this.game;
    }

    

    public static void main(String[] args) {
        Minesweeper game = new Minesweeper(10, 10, 13);
        Backtracker backtracker = new Backtracker(false);
        Configuration solution = backtracker.solve(new MinesweeperSolver(game));
        try {
            System.out.println(solution);
        } catch (NullPointerException e) {
            System.out.println("No solution found");
        }
    }
}

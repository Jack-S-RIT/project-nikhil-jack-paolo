package Part1_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import minesweeper.model.Location;
import minesweeper.model.Minesweeper;

public class MinesweeperTests {
    
    @Testable

    @Test
    public void testGetSelections(){
        Minesweeper minesweeper = new Minesweeper(2, 2, 2);
        Set<Location> expected = minesweeper.getPossibleSelection();
        String actual = "[{0, 1}, {1, 1}]";
        assertEquals(expected.toString(), actual);
    }

    @Test
    public void testcheckGameState(){
        Minesweeper minesweeper = new Minesweeper(2, 2, 2);
        boolean actual = true;
        assertEquals(minesweeper.checkGameState(), actual);
    }
}

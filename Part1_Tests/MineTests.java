package Part1_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import minesweeper.model.Location;
import minesweeper.model.Mine;

public class MineTests {
    @Testable

    @Test
    public void testEqualsCovered(){
        Mine expected = new Mine(new Location(1, 1));
        String actual = "-";
        assertEquals(expected.toString(), actual);
    }

    @Test
    public void testEqualsUncovered(){
        Mine expected = new Mine(new Location(1, 1));
        expected.flip();
        String actual = "M";
        assertEquals(expected.toString(), actual);
    }
}

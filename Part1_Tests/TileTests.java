package Part1_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import minesweeper.model.Location;
import minesweeper.model.Tile;

public class TileTests {
    @Testable

    @Test
    public void testTileEqualsOne() {
        Tile tile = new Tile(new Location(1, 1));
        Tile tile2 = new Tile(new Location(1, 1));
        assertEquals(tile.getValue(), tile2.getValue());
    }

    @Test
    public void testTileNotEquals() {
        Tile tile = new Tile(new Location(1, 1));
        Tile tile2 = new Tile(new Location(1, 2));
        assertNotEquals(tile.getLocation(), tile2.getLocation());
    }

    @Test
    public void testTileValue() {
        Tile tile = new Tile(new Location(1, 1));
        tile.setValue(8);
        int expected = tile.getValue();
        int actual = 8;
        assertEquals(expected, actual);
    }

    @Test
    public void testTileUncovered() {
        Tile tile = new Tile(new Location(1, 1));
        tile.flip();
        boolean expected = tile.isCovered();
        boolean actual = false;
        assertEquals(expected, actual);
    }

    @Test
    public void testTileCovered() {
        Tile tile = new Tile(new Location(1, 1));
        boolean expected = tile.isCovered();
        boolean actual = true;
        assertEquals(expected, actual);
    }
}

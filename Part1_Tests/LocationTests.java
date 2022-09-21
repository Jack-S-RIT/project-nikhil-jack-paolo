package Part1_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import minesweeper.model.Location;


public class LocationTests {
    @Testable
    
    @Test
    public void testLocationEquals(){
        Location exp = new Location(3, 3);
        Location res = new Location(3, 3);
        assertEquals(exp, res);
    }

    @Test
    public void testLocationDifferent(){
        Location exp = new Location(2, 2);
        Location res = new Location(3,3);
        assertNotEquals(exp, res);
    }

    @Test
    public void testGetRow(){
        Location one = new Location(3 , 3);
        int exp = one.getRow();
        int res = 3;
        assertEquals(exp, res);
    }

    @Test
    public void testGetCol(){
        Location one = new Location(3 , 3);
        int exp = one.getCol();
        int res = 3;
        assertEquals(exp, res);
    }
}

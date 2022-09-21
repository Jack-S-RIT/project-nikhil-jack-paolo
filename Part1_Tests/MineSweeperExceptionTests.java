package Part1_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import minesweeper.model.MineSweeperException;

public class MineSweeperExceptionTests {
    

    @Testable

    @Test
    public void testException(){
           MineSweeperException e = new MineSweeperException("Testing");
           String expected = e.getMessage();
           assertEquals(expected, "Testing");
    }
}

package Part1_Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import minesweeper.model.Board;
import minesweeper.model.Location;

public class BoardTests {
    @Testable

    @Test
    public void testMakeBoard(){
        Board expected = new Board(5, 5, 5);
        Board actual = new Board(5,5,5);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testEvaluate_TopLeft(){
        Board board = new Board(5, 5, 5);
        Location[] checks = board.evaluateTile(0, 0);
        int expected = board.determineValue(checks);
        int actual = 0;
        assertEquals(expected, actual);

    }

    @Test
    public void testEvaluate_TopRight(){
        Board board = new Board(5, 5, 5);
        Location[] checks = board.evaluateTile(0, 4);
        int expected = board.determineValue(checks);
        int actual = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testEvaluate_BottomLeft(){
        Board board = new Board(5, 5, 5);
        Location[] checks = board.evaluateTile(4, 0);
        int expected = board.determineValue(checks);
        int actual = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testEvaluate_BottomRight(){
        Board board = new Board(5,5,5);
        Location[] checks = board.evaluateTile(4, 0);
        int expected = board.determineValue(checks);
        int actual = 1;
        assertEquals(expected, actual);

    }

    @Test
    public void testEvaluate_TopEdge(){
        Board board = new Board(5,5,5);
        Location[] checks = board.evaluateTile(0, 2);
        int expected = board.determineValue(checks);
        int actual = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testEvaluate_BottomEdge(){
        Board board = new Board(5,5,5);
        Location[] checks = board.evaluateTile(4, 2);
        int expected = board.determineValue(checks);
        int actual = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testEvaluate_LeftEdge(){
        Board board = new Board(5,5,5);
        Location[] checks = board.evaluateTile(3, 0);
        int expected = board.determineValue(checks);
        int actual = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testEvaluate_RightEdge(){
        Board board = new Board(5,5,5);
        Location[] checks = board.evaluateTile(3, 4);
        int expected = board.determineValue(checks);
        int actual = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void testEvaluate_Middle(){
        Board board = new Board(10,10,20);
        Location[] checks = board.evaluateTile(7, 3);
        int expected = board.determineValue(checks);
        int actual = 3;
        assertEquals(expected, actual);
    }

}


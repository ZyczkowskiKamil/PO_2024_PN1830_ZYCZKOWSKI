package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static agh.ics.oop.OptionsParser.parseStringToMoveDirection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionsParserTest {

    @Test
    public void parseStringToMoveDirectionTest() {
        String[] input;
        MoveDirection[] expectedOutput;

        input = new String[] {"l", "r", "f", "b"};
        expectedOutput = new MoveDirection[] {MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD};
        assertTrue(Arrays.equals(expectedOutput, parseStringToMoveDirection(input)));

        input = new String[] {"ll"};
        expectedOutput = new MoveDirection[] {};
        assertTrue(Arrays.equals(expectedOutput, parseStringToMoveDirection(input)));

        input = new String[] {"rr"};
        expectedOutput = new MoveDirection[] {};
        assertTrue(Arrays.equals(expectedOutput, parseStringToMoveDirection(input)));

        input = new String[] {"ff"};
        expectedOutput = new MoveDirection[] {};
        assertTrue(Arrays.equals(expectedOutput, parseStringToMoveDirection(input)));

        input = new String[] {"bb"};
        expectedOutput = new MoveDirection[] {};
        assertTrue(Arrays.equals(expectedOutput, parseStringToMoveDirection(input)));

        input = new String[] {"l", "l", "l", "dsad", "dafas", "lfa", "yva", "@4", "1", "@"};
        expectedOutput = new MoveDirection[] {MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.LEFT};
        assertTrue(Arrays.equals(expectedOutput, parseStringToMoveDirection(input)));
    }
}

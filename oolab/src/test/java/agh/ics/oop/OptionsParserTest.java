package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.OptionsParser.parseStringToMoveDirection;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {

    @Test
    public void parseStringToMoveDirectionTest() {
        String[] input;
        MoveDirection[] expectedOutput;
        MoveDirection[] output;

        // empty string test
        input = new String[] {};
        expectedOutput = new MoveDirection[] {};
        output = parseStringToMoveDirection(input);
        assertArrayEquals(expectedOutput, output);

        input = new String[] {"l", "r", "f", "b"};
        expectedOutput = new MoveDirection[] {MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD};
        assertArrayEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"ll"};
        expectedOutput = new MoveDirection[] {};
        assertArrayEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"rr"};
        expectedOutput = new MoveDirection[] {};
        assertArrayEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"ff"};
        expectedOutput = new MoveDirection[] {};
        assertArrayEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"bb"};
        expectedOutput = new MoveDirection[] {};
        assertArrayEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"l", "l", "l", "dsad", "dafas", "lfa", "yva", "@4", "1", "@"};
        expectedOutput = new MoveDirection[] {MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.LEFT};
        assertArrayEquals(expectedOutput, parseStringToMoveDirection(input));
    }
}

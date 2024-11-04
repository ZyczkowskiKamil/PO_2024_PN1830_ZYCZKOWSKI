package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.OptionsParser.parseStringToMoveDirection;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {

    @Test
    public void parseStringToMoveDirectionTest() {
        String[] input;
        List<MoveDirection> expectedOutput;
        List<MoveDirection> output;

        // empty string list test
        input = new String[] {};
        expectedOutput = new ArrayList<>();
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));

        // empty string test
        input = new String[] {""};
        expectedOutput = new ArrayList<> ();
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));

        // basic directions test
        input = new String[] {"l", "r", "f", "b"};
        expectedOutput = new ArrayList<> (Arrays.asList(MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD));
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"ll"};
        expectedOutput = new ArrayList<> ();
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"rr"};
        expectedOutput = new ArrayList<> ();
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"ff"};
        expectedOutput = new ArrayList<> ();
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"bb"};
        expectedOutput = new ArrayList<> ();
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));

        input = new String[] {"l", "l", "l", "dsad", "dafas", "lfa", "yva", "@4", "1", "@"};
        expectedOutput = new ArrayList<> (Arrays.asList(MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.LEFT));
        assertIterableEquals(expectedOutput, parseStringToMoveDirection(input));
    }
}

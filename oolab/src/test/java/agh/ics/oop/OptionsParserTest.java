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
        List<MoveDirection> output = List.of();

        // empty string list test
        input = new String[] {};
        expectedOutput = new ArrayList<>();
        try {
            output = parseStringToMoveDirection(input);
            assertIterableEquals(expectedOutput, output);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // empty string test
        input = new String[] {""};
        expectedOutput = new ArrayList<> ();
        try {
            output = parseStringToMoveDirection(input);
            fail();
        } catch (IllegalArgumentException e) {
        }

        // basic directions test
        input = new String[] {"l", "r", "f", "b"};
        expectedOutput = new ArrayList<> (Arrays.asList(MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD));
        try {
            output = parseStringToMoveDirection(input);
            assertIterableEquals(expectedOutput, output);
        } catch (IllegalArgumentException e) {
            fail();
        }


        input = new String[] {"ll"};
        expectedOutput = new ArrayList<> ();
        try {
            output = parseStringToMoveDirection(input);
            fail();
        } catch (IllegalArgumentException e) {
        }


        input = new String[] {"rr"};
        expectedOutput = new ArrayList<> ();
        try {
            output = parseStringToMoveDirection(input);
            fail();
        } catch (IllegalArgumentException e) {
        }

        input = new String[] {"ff"};
        expectedOutput = new ArrayList<> ();
        try {
            output = parseStringToMoveDirection(input);
            fail();
        } catch (IllegalArgumentException e) {
        }

        input = new String[] {"bb"};
        expectedOutput = new ArrayList<> ();
        try {
            output = parseStringToMoveDirection(input);
            fail();
        } catch (IllegalArgumentException e) {
        }

        input = new String[] {"l", "l", "l", "dsad", "dafas", "lfa", "yva", "@4", "1", "@"};
        expectedOutput = new ArrayList<> (Arrays.asList(MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.LEFT));
        try {
            output = parseStringToMoveDirection(input);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }
}

package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class RectangularMapTest {

    @Test
    void place() {
        RectangularMap rectangularMap = new RectangularMap(5,5);

        try {
            rectangularMap.place(new Animal(2, 2));
            rectangularMap.place(new Animal(2, 3));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        try {
            // place is occupied
            rectangularMap.place(new Animal(2,3));
            fail("My method didn't throw when I expected it to");
        }
        catch (IncorrectPositionException ignored) {
        }

        try {
            // place out of map
            rectangularMap.place(new Animal(6,3));
            fail("My method didn't throw when I expected it to");
        }
        catch (IncorrectPositionException ignored) {
        }

        try {
            // place out of map
            rectangularMap.place(new Animal(3,6));
            fail("My method didn't throw when I expected it to");
        }
        catch (IncorrectPositionException ignored) {
        }
    }

    @Test
    void move() {
        RectangularMap rectangularMap = new RectangularMap(5,5);

        Animal animal1 = new Animal(2,2);
        Animal animal2 = new Animal(2,3);

        try {
            rectangularMap.place(new Animal(2,2));
            rectangularMap.place(new Animal(2,3));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), animal1.getPosition());
        assertEquals("N", animal1.toString());

        rectangularMap.move(animal2, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,4), animal2.getPosition());
        assertEquals("N", animal2.toString());

        rectangularMap.move(animal1, MoveDirection.LEFT);
        assertEquals(new Vector2d(2,2), animal1.getPosition());
        assertEquals("W", animal1.toString());

        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals("W", animal1.toString());

        rectangularMap.move(animal1, MoveDirection.RIGHT);
        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals("N", animal1.toString());

        rectangularMap.move(animal1, MoveDirection.BACKWARD);
        assertEquals(new Vector2d(1,1), animal1.getPosition());
        assertEquals("N", animal1.toString());

        // not moving out of map
        rectangularMap.move(animal1, MoveDirection.LEFT);
        rectangularMap.move(animal1, MoveDirection.FORWARD);
        rectangularMap.move(animal1, MoveDirection.FORWARD);
        rectangularMap.move(animal1, MoveDirection.FORWARD);
        rectangularMap.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(0,1), animal1.getPosition());
        assertEquals("W", animal1.toString());

        // animal not present on the map
        String currentMap = rectangularMap.toString();
        rectangularMap.move(new Animal(4,4), MoveDirection.FORWARD);
        assertEquals(currentMap, rectangularMap.toString());



    }

    @Test
    void isOccupied() {
        RectangularMap rectangularMap = new RectangularMap(5,5);

        try {
            rectangularMap.place(new Animal(2, 2));
            rectangularMap.place(new Animal(2, 3));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        assertTrue(rectangularMap.isOccupied(new Vector2d(2,3)));
        assertTrue(rectangularMap.isOccupied(new Vector2d(2,2)));
        assertFalse(rectangularMap.isOccupied(new Vector2d(2,4)));
    }

    @Test
    void objectAt() {
        RectangularMap rectangularMap = new RectangularMap(5,5);

        Animal animal1 = new Animal(2,2);
        Animal animal2 = new Animal(2,3);

        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        assertEquals(animal1, rectangularMap.objectAt(new Vector2d(2,2)));
        assertEquals(animal2, rectangularMap.objectAt(new Vector2d(2,3)));
        assertNull(rectangularMap.objectAt(new Vector2d(2,4)));
        assertNull(rectangularMap.objectAt(new Vector2d(5,5)));
    }

    @Test
    void canMoveTo() {
        RectangularMap rectangularMap = new RectangularMap(5,5);

        try {
            rectangularMap.place(new Animal(2,2));
            rectangularMap.place(new Animal(2,3));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        assertFalse(rectangularMap.canMoveTo(new Vector2d(2,2)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(2,3)));
        assertTrue(rectangularMap.canMoveTo(new Vector2d(2,4)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(3,5)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(5,3)));
        assertTrue(rectangularMap.canMoveTo(new Vector2d(0,0)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(0,-1)));
    }

    @Test
    void toStringTest() {
        RectangularMap rectangularMap = new RectangularMap(5,5);

        String testString = new String(rectangularMap.toString());
        String ans = new String(
                  " y\\x  0 1 2 3 4\n"
                + "  5: -----------\n"
                + "  4: | | | | | |\n"
                + "  3: | | | | | |\n"
                + "  2: | | | | | |\n"
                + "  1: | | | | | |\n"
                + "  0: | | | | | |\n"
                + " -1: -----------\n");
        assertEquals(ans, testString);
    }

    @Test
    void getElementsTest() {
        RectangularMap rectangularMap = new RectangularMap(5,5);

        Animal animal1 = new Animal(2,2);
        Animal animal2 = new Animal(2,3);

        try {
            rectangularMap.place(new Animal(2,2));
            rectangularMap.place(new Animal(2,3));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        ArrayList<WorldElement> elements = new ArrayList<>(rectangularMap.getElements());
        ArrayList<WorldElement> correctElements = new ArrayList<>(
                Arrays.asList(animal1,animal2)
        );

        assertEquals(elements.size(), correctElements.size());

        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i).getPosition(), correctElements.get(i).getPosition());
        }

        ArrayList<WorldElement> incorrectElements = new ArrayList<>(
                Arrays.asList(new Animal(2,2),animal2)
        );

        assertNotEquals(incorrectElements, elements);
    }
}
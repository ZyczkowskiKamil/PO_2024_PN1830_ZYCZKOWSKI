package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {

    @Test
    void place() {
        GrassField grassField = new GrassField(10);

        try {
            grassField.place(new Animal(2,2));
            grassField.place(new Animal(2,3));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        // place is occupied
        try {
            grassField.place(new Animal(2, 3));
            fail("My method didn't throw when I expected it to");
        }
        catch (IncorrectPositionException ignored) {
        }
    }

    @Test
    void move() {
        GrassField grassField = new GrassField(10);
        Animal animal1 = new Animal(2,2);
        Animal animal2 = new Animal(2,3);
        try {
            grassField.place(animal1);
            grassField.place(animal2);
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        grassField.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,2), animal1.getPosition());
        assertEquals("N", animal1.toString());

        grassField.move(animal2, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,4), animal2.getPosition());
        assertEquals("N", animal2.toString());

        grassField.move(animal1, MoveDirection.LEFT);
        assertEquals(new Vector2d(2,2), animal1.getPosition());
        assertEquals("W", animal1.toString());

        grassField.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals("W", animal1.toString());

        grassField.move(animal1, MoveDirection.RIGHT);
        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals("N", animal1.toString());

        grassField.move(animal1, MoveDirection.BACKWARD);
        assertEquals(new Vector2d(1,1), animal1.getPosition());
        assertEquals("N", animal1.toString());

        // moving to negative coordinates
        grassField.move(animal1, MoveDirection.LEFT);
        grassField.move(animal1, MoveDirection.FORWARD);
        grassField.move(animal1, MoveDirection.FORWARD);
        grassField.move(animal1, MoveDirection.FORWARD);
        grassField.move(animal1, MoveDirection.FORWARD);
        assertEquals(new Vector2d(-3,1), animal1.getPosition());
        assertEquals("W", animal1.toString());

        // animal not present on the map
        String currentMap = grassField.toString();
        grassField.move(new Animal(4,4), MoveDirection.FORWARD);
        assertEquals(currentMap, grassField.toString());

    }

    @Test
    void isOccupied() {
        GrassField grassField = new GrassField(10);

        try {
            grassField.place(new Animal(2, 2));
            grassField.place(new Animal(2, 3));
        }
        catch (IncorrectPositionException e) {
            fail();
        }

        assertTrue(grassField.isOccupied(new Vector2d(2,3)));
        assertTrue(grassField.isOccupied(new Vector2d(2,2)));
        assertFalse(grassField.isOccupied(new Vector2d(2,4)));
    }

    @Test
    void objectAt() {
        GrassField grassField = new GrassField(10);

        Animal animal1 = new Animal(2,2);
        Animal animal2 = new Animal(2,3);

        try {
            grassField.place(animal1);
            grassField.place(animal2);
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        assertEquals(animal1, grassField.objectAt(new Vector2d(2,2)));
        assertEquals(animal2, grassField.objectAt(new Vector2d(2,3)));
        assertNull(grassField.objectAt(new Vector2d(-1,-1)));
        assertNull(grassField.objectAt(new Vector2d(0,10)));
    }

    @Test
    void canMoveTo() {
        GrassField grassField = new GrassField(10);

        try {
            grassField.place(new Animal(2, 2));
            grassField.place(new Animal(2, 3));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        assertFalse(grassField.canMoveTo(new Vector2d(2,2)));
        assertFalse(grassField.canMoveTo(new Vector2d(2,3)));
        assertTrue(grassField.canMoveTo(new Vector2d(2,4)));
        assertTrue(grassField.canMoveTo(new Vector2d(-3,-5)));
        assertTrue(grassField.canMoveTo(new Vector2d(-5,-3)));
        assertTrue(grassField.canMoveTo(new Vector2d(0,0)));
        assertTrue(grassField.canMoveTo(new Vector2d(0,-1)));
    }

    @Test
    void getCurrentBounds() {
        GrassField grassField = new GrassField(10);

        try {
            grassField.place(new Animal(-3, 12));
            grassField.place(new Animal(0, 0));
            grassField.place(new Animal(12, -5));
        }
        catch (IncorrectPositionException e) {
            fail(e);
        }

        Boundary boundary = grassField.getCurrentBounds();
        Boundary correctBoundary = new Boundary(new Vector2d(-3,-5), new Vector2d(12,12));

        assertEquals(correctBoundary, boundary);
    }

}

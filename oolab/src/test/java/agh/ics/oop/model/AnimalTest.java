package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    public void animalConstructorTest() {
        Animal animal = new Animal();
        assertEquals(new Vector2d(2,2), animal.getPosition());
        assertEquals("N", animal.toString());

        animal = new Animal(3,3);
        assertEquals(new Vector2d(3,3), animal.getPosition());
        assertEquals("N", animal.toString());

//        animal = new Animal(5,4);
//        assertEquals(new Vector2d(2,2), animal.getPosition());
//        assertEquals("N", animal.toString());
//
//        animal = new Animal(0,5);
//        assertEquals(new Vector2d(2,2), animal.getPosition());
//        assertEquals("N", animal.toString());
//
//        animal = new Animal(-10,-10);
//        assertEquals(new Vector2d(2,2), animal.getPosition());
//        assertEquals("N", animal.toString());
//
//        animal = new Animal(new Vector2d(3,3));
//        assertEquals(new Vector2d(3,3), animal.getPosition());
//        assertEquals("N", animal.toString());
//
//        animal = new Animal(new Vector2d(5,4));
//        assertEquals(new Vector2d(2,2), animal.getPosition());
//        assertEquals("N", animal.toString());
    }

    @Test
    public void isAt() {
        Animal animal = new Animal(2,2);
        Vector2d position = new Vector2d(2,2);
        assertTrue(animal.isAt(position));

        position = new Vector2d(2,3);
        assertFalse(animal.isAt(position));

        position = new Vector2d(3,2);
        assertFalse(animal.isAt(position));

        position = new Vector2d(3,3);
        assertFalse(animal.isAt(position));
    }

    @Test
    public void move_and_toStringTest() {
        MoveValidator validator = new MoveValidator() {
            final Vector2d upperRight = new Vector2d(4,4);
            final Vector2d bottomLeft = new Vector2d(1,1);

            @Override
            public boolean canMoveTo(Vector2d position) {
                return position.follows(bottomLeft) && position.precedes(upperRight);
            }
        };

        Animal animal = new Animal(2,2);
        animal.move(MoveDirection.RIGHT,validator);
        animal.move(MoveDirection.FORWARD,validator);
        assertEquals(new Vector2d(3,2), animal.getPosition());
        assertEquals("E", animal.toString());

        animal = new Animal(1,1);
        animal.move(MoveDirection.BACKWARD,validator);
        assertEquals(new Vector2d(1,1), animal.getPosition());
        assertEquals("N", animal.toString());

        animal = new Animal(1,4);
        animal.move(MoveDirection.LEFT,validator);
        animal.move(MoveDirection.FORWARD,validator);
        assertEquals(new Vector2d(1,4), animal.getPosition());
        assertEquals("W", animal.toString());

        animal = new Animal(4,4);
        animal.move(MoveDirection.RIGHT,validator);
        animal.move(MoveDirection.FORWARD,validator);
        assertEquals(new Vector2d(4,4), animal.getPosition());
        assertEquals("E", animal.toString());

    }
}

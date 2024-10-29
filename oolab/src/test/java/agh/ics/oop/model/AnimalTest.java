package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    public void animalConstructorTest() {
        Animal animal = new Animal();
        assertEquals("(2,2) polnoc", animal.toString());

        animal = new Animal(3,3);
        assertEquals("(3,3) polnoc", animal.toString());

        animal = new Animal(5,4);
        assertEquals("(2,4) polnoc", animal.toString());

        animal = new Animal(0,5);
        assertEquals("(0,2) polnoc", animal.toString());

        animal = new Animal(-10,-10);
        assertEquals("(2,2) polnoc", animal.toString());
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
        Animal animal = new Animal(2,2);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertEquals("(3,2) wschod", animal.toString());

        animal = new Animal(0,0);
        animal.move(MoveDirection.BACKWARD);
        assertEquals("(0,0) polnoc", animal.toString());

        animal = new Animal(0,4);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals("(0,4) zachod", animal.toString());
    }
}

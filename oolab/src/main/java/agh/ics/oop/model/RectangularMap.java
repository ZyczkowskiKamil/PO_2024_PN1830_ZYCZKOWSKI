package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private static final int height = 4;
    private static final int width = 4;

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsKey(animal.getPosition()) && animals.containsValue(animal)) {
            // animal is present on the map
            animals.remove(animal.getPosition());
            animal.move(direction);
            if (canMoveTo(animal.getPosition())) {
                animals.put(animal.getPosition(), animal);
            }
            else {
                switch (direction) {
                    case LEFT: animal.move(MoveDirection.RIGHT);
                    case RIGHT: animal.move(MoveDirection.LEFT);
                    case FORWARD: animal.move(MoveDirection.BACKWARD);
                    case BACKWARD: animal.move(MoveDirection.FORWARD);
                }
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if (isOccupied(position)) {
            return animals.get(position);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (isOccupied(position)) {
            return false;
        }
        if (position.getX() < 0 || position.getX() > width ||
            position.getY() < 0 || position.getY() > height) {
            return false;
        }
        return true;
    }
}

package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final int width;
    private final int height;
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;

        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width-1, height-1);
    }

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
        return !isOccupied(position) && position.follows(lowerLeft) && position.precedes(upperRight);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}

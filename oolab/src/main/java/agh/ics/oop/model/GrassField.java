package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    private Vector2d grassUpperRight;
    private Vector2d grassLowerLeft;

    private Vector2d mapUpperRight;
    private Vector2d mapLowerLeft;

    public GrassField(int grassObjectsNumber) {

        Random rand = new Random();
        int grassBoundX = (int) sqrt(grassObjectsNumber*10);
        int grassBoundY = (int) sqrt(grassObjectsNumber*10);
        this.grassUpperRight = new Vector2d(grassBoundX,grassBoundY);
        this.grassLowerLeft = new Vector2d(0,0);

        for (int i = 0; i < grassObjectsNumber; i++) {
            int posX = (int) rand.nextInt(grassBoundX);
            int posY = (int) rand.nextInt(grassBoundY);
            Grass grass = new Grass(new Vector2d(posX, posY));
            if (grasses.containsKey(grass.getPosition()))
                i--;
            else {
                grasses.put(grass.getPosition(), grass);
                grassUpperRight = grassUpperRight.upperRight(grass.getPosition());
                grassLowerLeft = grassLowerLeft.lowerLeft(grass.getPosition());
            }
        }

        updateMapSizeVectors();
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
        if (animals.containsKey(animal.getPosition())) {
            animals.remove(animal.getPosition());
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) return animals.get(position);
        if (grasses.containsKey(position)) return grasses.get(position);
        return null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    private void updateMapSizeVectors() {
        mapUpperRight = new Vector2d(grassUpperRight.getX(), grassUpperRight.getY());
        mapLowerLeft = new Vector2d(grassLowerLeft.getX(), grassLowerLeft.getY());

        List<Vector2d> animalPositions = new ArrayList<>(animals.keySet());
        for (Vector2d position : animalPositions) {
            mapUpperRight = mapUpperRight.upperRight(position);
            mapLowerLeft = mapLowerLeft.lowerLeft(position);
        }
    }

    @Override
    public String toString() {
        updateMapSizeVectors();
        return mapVisualizer.draw(mapLowerLeft,mapUpperRight);
    }
}
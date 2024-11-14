package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements WorldMap  {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

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
            int posX = rand.nextInt(grassBoundX);
            int posY = rand.nextInt(grassBoundY);
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
    public boolean isOccupied(Vector2d position) {
        return grasses.containsKey(position) || super.isOccupied(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) return animals.get(position);
        if (grasses.containsKey(position)) return grasses.get(position);
        return null;
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

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }
}
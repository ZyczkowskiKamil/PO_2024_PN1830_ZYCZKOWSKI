package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> animalsList = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap worldMap;
    private int animalsOnMap = 0;


    public Simulation(List<Vector2d> animalPositions , List<MoveDirection> moves, WorldMap worldMap) {
        this.moves = moves;
        this.worldMap = worldMap;
        for (Vector2d animalPosition : animalPositions) {
            try {
                this.worldMap.place(new Animal(animalPosition));
                animalsOnMap++;
                animalsList.add(new Animal(animalPosition));
            }
            catch (IncorrectPositionException e) {
                System.out.println(e);
            }
        }
    }

    public void run() {
        if (animalsOnMap == 0) { // there are no animals to move
            return;
        }

        int nextAnimalID = 0;
        for (MoveDirection move : moves) {
            Animal animal = animalsList.get(nextAnimalID);
            worldMap.move(animal, move);

            nextAnimalID++;
            if (nextAnimalID >= animalsOnMap) nextAnimalID = 0;
        }
    }

    public List<Animal> getAnimalsList() {
        return animalsList;
    }
}

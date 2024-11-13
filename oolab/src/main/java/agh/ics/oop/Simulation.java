package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animalsList = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap worldMap;
    private int animalsOnMap = 0;


    public Simulation(List<Vector2d> animalPositions , List<MoveDirection> moves, WorldMap worldMap) {
        this.moves = moves;
        this.worldMap = worldMap;
        for (Vector2d animalPosition : animalPositions) {
            if (this.worldMap.place(new Animal(animalPosition))) {
                animalsOnMap++;
                animalsList.add(new Animal(animalPosition));
            }
        }
    }

    public void run() {

        int nextAnimalID = 0;

        if (animalsOnMap == 0) { // there are no animals to move
            return;
        }

        System.out.println(worldMap + "\n");
        for (MoveDirection move : moves) {
            Animal animal = animalsList.get(nextAnimalID);
            worldMap.move(animal, move);

            nextAnimalID++;
            if (nextAnimalID >= animalsOnMap) nextAnimalID = 0;

            System.out.println(worldMap + "\n");
        }
    }

    public List<Animal> getAnimalsList() {
        return animalsList;
    }
}

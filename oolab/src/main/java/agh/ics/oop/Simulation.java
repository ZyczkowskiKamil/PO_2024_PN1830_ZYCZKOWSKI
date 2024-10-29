package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> animalPositions , List<MoveDirection> moves) {
        this.moves = moves;
        for (Vector2d animalPosition : animalPositions) {
            this.animals.add(new Animal(animalPosition.getX(), animalPosition.getY()));
        }
    }

    public void run() {

        int nextAnimalID = 0;
        int numberOfAnimals = animals.size();

        if (numberOfAnimals == 0) { // there are no animals to move
            return;
        }

        for (MoveDirection move : moves) {
            animals.get(nextAnimalID).move(move);

            System.out.println("Zwierze " + nextAnimalID + " : " + animals.get(nextAnimalID).toString());

            nextAnimalID++;
            if (nextAnimalID >= numberOfAnimals) nextAnimalID = 0;
        }
    }
}

package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animalsList = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> animalPositions , List<MoveDirection> moves) {
        this.moves = moves;
        for (Vector2d animalPosition : animalPositions) {
            this.animalsList.add(new Animal(animalPosition.getX(), animalPosition.getY()));
        }
    }

    public void run() {

        int nextAnimalID = 0;
        int numberOfAnimals = animalsList.size();

        if (numberOfAnimals == 0) { // there are no animals to move
            return;
        }

//        for (MoveDirection move : moves) {
//            animalsList.get(nextAnimalID).move(move);
//
//            System.out.println("Zwierze " + nextAnimalID + " : " + animalsList.get(nextAnimalID).toString());
//
//            nextAnimalID++;
//            if (nextAnimalID >= numberOfAnimals) nextAnimalID = 0;
//        }
    }

    public List<Animal> getAnimalsList() {
        return animalsList;
    }
}

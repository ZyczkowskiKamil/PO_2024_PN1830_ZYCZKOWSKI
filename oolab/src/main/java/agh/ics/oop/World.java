package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.*;

public class World {

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap worldMap = new GrassField(10);
        Simulation simulation = new Simulation(positions, directions, worldMap);

        ConsoleMapDisplay firstObserver = new ConsoleMapDisplay();
        worldMap.addObserver(firstObserver);

        simulation.run();

//        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(args);
//        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//        WorldMap worldMap = new GrassField(10);
//        Simulation simulation = new Simulation(positions, directions, worldMap);
//        simulation.run();
    }

    private static String getMoveMessage(MoveDirection direction) {
        return switch (direction) {
            case MoveDirection.FORWARD -> "Zwierzak idzie do przodu";
            case MoveDirection.BACKWARD -> "Zwierzak idzie do tyłu";
            case MoveDirection.RIGHT -> "Zwierzak skręca w prawo";
            case MoveDirection.LEFT -> "Zwierzak skręca w lewo";
        };
    }

    public static void run(MoveDirection[] moveDirections) {

        for (MoveDirection direction : moveDirections) {
            System.out.println(getMoveMessage(direction));
        }
    }

}

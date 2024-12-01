package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.*;

public class World {

    public static void main(String[] args) {
        System.out.println("System rozpoczął działanie");

        List<Simulation> simulations = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection("l r f b".split(" "));
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            AbstractWorldMap worldMap = new GrassField(10);
            Simulation simulation = new Simulation(positions, directions, worldMap);

            ConsoleMapDisplay observer = new ConsoleMapDisplay();
            worldMap.addObserver(observer);

            simulations.add(simulation);
        }

        SimulationEngine simulationEngine = new SimulationEngine(simulations);

        simulationEngine.runAsync();
        try {
            simulationEngine.awaitSimulationsEnd();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(args);
//        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//        WorldMap worldMap = new GrassField(10);
//        Simulation simulation = new Simulation(positions, directions, worldMap);
//        simulation.run();

        System.out.println("System zakończył działanie");
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

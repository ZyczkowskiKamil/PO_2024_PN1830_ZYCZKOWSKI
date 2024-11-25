package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.*;

public class World {

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap worldMap = new GrassField(10);
        Simulation simulation = new Simulation(positions, directions, worldMap);

        List<MoveDirection> directions2 = OptionsParser.parseStringToMoveDirection("l r f b".split(" "));
        List<Vector2d> positions2 = List.of(new Vector2d(3,3), new Vector2d(4,5));
        AbstractWorldMap worldMap2 = new GrassField(10);
        Simulation simulation2 = new Simulation(positions2, directions2, worldMap2);

        List<MoveDirection> directions3 = OptionsParser.parseStringToMoveDirection("r r r r".split(" "));
        List<Vector2d> positions3 = List.of(new Vector2d(1,1), new Vector2d(1,2));
        AbstractWorldMap worldMap3 = new RectangularMap(5,5);
        Simulation simulation3 = new Simulation(positions3, directions3, worldMap3);

        ConsoleMapDisplay firstObserver = new ConsoleMapDisplay();
        worldMap.addObserver(firstObserver);
        ConsoleMapDisplay secondObserver = new ConsoleMapDisplay();
        worldMap2.addObserver(secondObserver);
        ConsoleMapDisplay thirdObserver = new ConsoleMapDisplay();
        worldMap3.addObserver(thirdObserver);

        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation, simulation2, simulation3));
        simulationEngine.runSync();


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

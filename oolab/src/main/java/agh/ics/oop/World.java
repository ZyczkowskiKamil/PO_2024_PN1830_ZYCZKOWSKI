package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.*;

public class World {

    public static void main(String[] args) {


//        System.out.println("System rozpoczął działanie");
//
//        List<Simulation> simulations = new LinkedList<>();
//
//        for (int i = 0; i < 1000; i++) {
//            List<MoveDirection> directions = new LinkedList<>();
//            try {
//                directions = OptionsParser.parseStringToMoveDirection("l r f b".split(" "));
//            }
//            catch (IllegalArgumentException e) {
//                System.out.println("Zły argument w options parser");
//                e.printStackTrace();
//            }
//            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//            AbstractWorldMap worldMap = new GrassField(10);
//            Simulation simulation = new Simulation(positions, directions, worldMap);
//
//            ConsoleMapDisplay observer = new ConsoleMapDisplay();
//            worldMap.addObserver(observer);
//
//            simulations.add(simulation);
//        }
//
//        SimulationEngine simulationEngine = new SimulationEngine(simulations);
//
//        try {
//            simulationEngine.runAsyncInThreadPool();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("System zakończył działanie");
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

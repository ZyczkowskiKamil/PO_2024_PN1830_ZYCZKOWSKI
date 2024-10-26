package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        run(args);
        System.out.println("System zakończył działanie");
        System.out.println("Start");
        MoveDirection[] moveDirections = OptionsParser.parseStringToMoveDirection(args);
        run(moveDirections);
        System.out.println("Stop");
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

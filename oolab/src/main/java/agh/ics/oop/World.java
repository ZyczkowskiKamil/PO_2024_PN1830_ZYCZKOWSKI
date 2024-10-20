package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        run(args);
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

    public static void run(String[] args) {
        MoveDirection[] moveDirections = OptionsParser.parseStringToMoveDirection(args);

        for (MoveDirection direction : moveDirections) {
            System.out.println(getMoveMessage(direction));
        }

    }
}

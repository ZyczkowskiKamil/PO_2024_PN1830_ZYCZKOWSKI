package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        run(args);
        System.out.println("System zakończył działanie");
    }

    public static void run(String[] args) {
        MoveDirection[] moveDirections = OptionsParser.parseStringToMoveDirection(args);
        if (moveDirections.length > 0) {
            String nextMove = switch (moveDirections[0]) {
                case MoveDirection.FORWARD -> "Zwierzak idzie do przodu";
                case MoveDirection.BACKWARD -> "Zwierzak idzie do tyłu";
                case MoveDirection.RIGHT -> "Zwierzak skręca w prawo";
                case MoveDirection.LEFT -> "Zwierzak skręca w lewo";
            };
            System.out.println(nextMove);
        }
        for (int i = 1; i < moveDirections.length; i++) {
            String nextMove = switch (moveDirections[i]) {
                case MoveDirection.FORWARD -> "Zwierzak idzie do przodu";
                case MoveDirection.BACKWARD -> "Zwierzak idzie do tyłu";
                case MoveDirection.RIGHT -> "Zwierzak skręca w prawo";
                case MoveDirection.LEFT -> "Zwierzak skręca w lewo";
            };
            System.out.println(nextMove);
        }

    }
}

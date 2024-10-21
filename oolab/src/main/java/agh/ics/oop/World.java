package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        run(args);
        System.out.println("System zakończył działanie");

//        MapDirection dir = MapDirection.EAST;
//        System.out.println(dir);
//        System.out.println(dir.toString());
//        System.out.println(dir.next());
//        System.out.println(dir.previous());
//        System.out.println(dir.toUnitVector());

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

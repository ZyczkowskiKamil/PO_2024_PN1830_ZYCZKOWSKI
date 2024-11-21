package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parseStringToMoveDirection(String[] directions) {

        List<MoveDirection> moveDirectionsList = new ArrayList<>();
        for (String direction : directions) {
            switch (direction) {
                case "f", "forward" -> moveDirectionsList.add(MoveDirection.FORWARD);
                case "b", "backward" -> moveDirectionsList.add(MoveDirection.BACKWARD);
                case "r", "right" -> moveDirectionsList.add(MoveDirection.RIGHT);
                case "l", "left" -> moveDirectionsList.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(direction + " is not a valid move specification");
            }
        }

        return moveDirectionsList;
    }

}

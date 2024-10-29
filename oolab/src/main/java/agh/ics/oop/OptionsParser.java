package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parseStringToMoveDirection(String[] directions) {

        List<MoveDirection> moveDirectionsList = new ArrayList<>();
        for (String direction : directions) {
            switch (direction) {
                case "f" -> moveDirectionsList.add(MoveDirection.FORWARD);
                case "b" -> moveDirectionsList.add(MoveDirection.BACKWARD);
                case "r" -> moveDirectionsList.add(MoveDirection.RIGHT);
                case "l" -> moveDirectionsList.add(MoveDirection.LEFT);
            }
        }

        return moveDirectionsList;
    }

}

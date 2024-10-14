package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parseStringToMoveDirection(String[] directions) {

        MoveDirection[] moveDirectionsConverted = new MoveDirection[directions.length];
        int counter = 0;
        for (String direction : directions) {
            switch (direction) {
                case "f" -> {
                    moveDirectionsConverted[counter] = MoveDirection.FORWARD;
                    counter++;
                }
                case "b" -> {
                    moveDirectionsConverted[counter] = MoveDirection.BACKWARD;
                    counter++;
                }
                case "r" -> {
                    moveDirectionsConverted[counter] = MoveDirection.RIGHT;
                    counter++;
                }
                case "l" -> {
                    moveDirectionsConverted[counter] = MoveDirection.LEFT;
                    counter++;
                }
            }
        }

        MoveDirection[] moveDirectionsConvertedTrimmed = new MoveDirection[counter];
        System.arraycopy(moveDirectionsConverted, 0, moveDirectionsConvertedTrimmed, 0, counter);

        return moveDirectionsConvertedTrimmed;
    }

}

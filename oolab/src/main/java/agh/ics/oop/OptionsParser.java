package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parseStringToMoveDirection(String[] directions) {

        MoveDirection[] moveDirectionsConverted = new MoveDirection[directions.length];
        int validMoves = 0;
        for (String direction : directions) {
            switch (direction) {
                case "f" -> {
                    moveDirectionsConverted[validMoves] = MoveDirection.FORWARD;
                    validMoves++;
                }
                case "b" -> {
                    moveDirectionsConverted[validMoves] = MoveDirection.BACKWARD;
                    validMoves++;
                }
                case "r" -> {
                    moveDirectionsConverted[validMoves] = MoveDirection.RIGHT;
                    validMoves++;
                }
                case "l" -> {
                    moveDirectionsConverted[validMoves] = MoveDirection.LEFT;
                    validMoves++;
                }
            }
        }

        MoveDirection[] moveDirectionsConvertedTrimmed = new MoveDirection[validMoves];
        System.arraycopy(moveDirectionsConverted, 0, moveDirectionsConvertedTrimmed, 0, validMoves);

        return moveDirectionsConvertedTrimmed;
    }

}

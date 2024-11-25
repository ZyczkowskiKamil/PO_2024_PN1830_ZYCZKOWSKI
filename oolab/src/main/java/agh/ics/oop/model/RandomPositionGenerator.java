package agh.ics.oop.model;

import java.util.*;

public class RandomPositionGenerator implements Iterator<Vector2d>, Iterable<Vector2d> {
    // generate random positions
    // width from [0,width-1]
    // height from [0,height-1]

    // algorithm tries to generate all random with 3*n tries, then
    // generate random positions from not used positions

    private final LinkedList<Vector2d> randomPositions = new LinkedList<>();
    private final Iterator<Vector2d> randomPositionIterator;
    private final int width;

    public RandomPositionGenerator(int width, int height, int grassCount) {
        this.width = width;

        int possiblePositions = width * height;
        int randomCounter = 0;
        int grassesToPlace = grassCount;
        Random rand = new Random();
        HashSet<Integer> notUsedPositionID = new HashSet<>();

        for (int i = 0; i < possiblePositions; i++) {
            notUsedPositionID.add(i);
        }

        while ((grassesToPlace > 0) && (randomCounter < (possiblePositions * 3))) {
            int positionID = rand.nextInt(0,possiblePositions);
            if (notUsedPositionID.contains(positionID)) {
                notUsedPositionID.remove(positionID);
                randomPositions.add(calculateIdToPosition(positionID));
                grassesToPlace--;
            }
        }

        ArrayList<Integer> availablePositionID = new ArrayList<>(notUsedPositionID);
        while (grassesToPlace > 0) {
            int positionID= rand.nextInt(0,possiblePositions-(grassCount-grassesToPlace));
            randomPositions.add(calculateIdToPosition(positionID));
            availablePositionID.remove(positionID);
            grassesToPlace--;
        }

        this.randomPositionIterator = randomPositions.iterator();
    }

    // generate position vector from positionID number from order
    private Vector2d calculateIdToPosition(int positionID) {
        int positionY = positionID / width;
        int positionX = positionID % width;
        return new Vector2d(positionX, positionY);
    }


    @Override
    public boolean hasNext() {
        return randomPositionIterator.hasNext();
    }

    @Override
    public Vector2d next() {
        return randomPositionIterator.next();
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return randomPositionIterator;
    }
}

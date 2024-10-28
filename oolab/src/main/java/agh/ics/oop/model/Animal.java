package agh.ics.oop.model;

public class Animal {
    private MapDirection animalOrientation;
    private Vector2d position;

    private static final int mapBorderNorth = 4;
    private static final int mapBorderSouth = 0;
    private static final int mapBorderEast = 4;
    private static final int mapBorderWest = 0;


    public Animal() {
        this(2,2);
    }

    public Animal(int x, int y) {
        this.position = new Vector2d(x,y);
        this.animalOrientation = MapDirection.NORTH;
    }

    public String toString() {
        return "(" + position.getX() + "," + position.getY() + ") " + animalOrientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.getX() == position.getX() && this.position.getY() == position.getY();
    }

    public boolean checkIfAnimalIsInMap() {
        return position.getX() >= mapBorderWest && position.getX() <= mapBorderEast &&
                position.getY() >= mapBorderSouth && position.getY() <= mapBorderNorth;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case MoveDirection.FORWARD -> {
                position = position.add(animalOrientation.toUnitVector());
                if (!checkIfAnimalIsInMap()) position = position.subtract(animalOrientation.toUnitVector());
            }
            case MoveDirection.BACKWARD -> {
                position = position.subtract(animalOrientation.toUnitVector());
                if (!checkIfAnimalIsInMap()) position = position.add(animalOrientation.toUnitVector());
            }
            case MoveDirection.RIGHT -> {
                animalOrientation = animalOrientation.next();
            }
            case MoveDirection.LEFT -> {
                animalOrientation = animalOrientation.previous();
            }
        }
    }

}

package agh.ics.oop.model;

public class Animal {
    private MapDirection animalOrientation;
    private Vector2d position;

    private static final int mapBorderNorth = 4;
    private static final int mapBorderSouth = 0;
    private static final int mapBorderEast = 4;
    private static final int mapBorderWest = 0;
    private static final Vector2d defaultAnimalPosition = new Vector2d(2,2);


    public Animal() {
        this(defaultAnimalPosition.getX(),defaultAnimalPosition.getY());
    }

    public Animal(Vector2d position) {
        this(position.getX(),position.getY());
    }

    public Animal(int x, int y) {
        this.position = new Vector2d(x,y);
        this.animalOrientation = MapDirection.NORTH;

        if (!isAnimalInMap()) position = new Vector2d(defaultAnimalPosition.getX(),defaultAnimalPosition.getY());
    }

    @Override
    public String toString() {
        return switch (animalOrientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.getX() == position.getX() && this.position.getY() == position.getY();
    }

    private boolean isAnimalInMap() {
        return position.getX() >= mapBorderWest && position.getX() <= mapBorderEast &&
                position.getY() >= mapBorderSouth && position.getY() <= mapBorderNorth;
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case MoveDirection.FORWARD -> {
                position = position.add(animalOrientation.toUnitVector());
                if (!validator.canMoveTo(position))
                    position = position.subtract(animalOrientation.toUnitVector());
            }
            case MoveDirection.BACKWARD -> {
                position = position.subtract(animalOrientation.toUnitVector());
                if (!validator.canMoveTo(position))
                    position = position.add(animalOrientation.toUnitVector());
            }
            case MoveDirection.RIGHT -> {
                animalOrientation = animalOrientation.next();
            }
            case MoveDirection.LEFT -> {
                animalOrientation = animalOrientation.previous();
            }
        }
    }

    public Vector2d getPosition() {
        return this.position;
    }

}

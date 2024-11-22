package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int updateCounter = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        updateCounter++;
        System.out.println();
        System.out.println(message);
        System.out.print(worldMap.toString());
        System.out.println("Update number: " + updateCounter);
    }
}

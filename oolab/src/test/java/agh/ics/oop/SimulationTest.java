package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {

    @Test
    public void integrationTest1() {
        String[] input = "l r l f".split(" ");
        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap worldMap = new RectangularMap(6,6);
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

        List<Vector2d> correctPositions = List.of(new Vector2d(2,2), new Vector2d(4,4));
        List<MapDirection> correctOrientations = List.of(MapDirection.SOUTH, MapDirection.EAST);

        List<Vector2d> actualPositions = new ArrayList<>();
        List<MapDirection> actualOrientations = new ArrayList<>();

        List<Animal> animals = simulation.getAnimalsList();
        for (Animal animal : animals) {
            actualPositions.add(animal.getPosition());
            actualOrientations.add(animal.getOrientation());
        }

        assertEquals(correctPositions, actualPositions);
        assertEquals(correctOrientations, actualOrientations);
    }

    @Test
    public void integrationTest2() { // moves trying to collide and go out of border
        String[] input = "f b r l f f r r f f f f f f f f f r".split(" ");
        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(2,2));
        WorldMap worldMap = new RectangularMap(6,6);
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

        List<Vector2d> correctPositions = List.of(new Vector2d(2,0), new Vector2d(3,5));
        List<MapDirection> correctOrientations = List.of(MapDirection.SOUTH, MapDirection.EAST);

        List<Vector2d> actualPositions = new ArrayList<>();
        List<MapDirection> actualOrientations = new ArrayList<>();

        List<Animal> animals = simulation.getAnimalsList();
        for (Animal animal : animals) {
            actualPositions.add(animal.getPosition());
            actualOrientations.add(animal.getOrientation());
        }

        assertEquals(correctPositions, actualPositions);
        assertEquals(correctOrientations, actualOrientations);
    }

    @Test
    public void integrationTest3() { // test if not correct input is eliminated
        String[] input = "l r l f fafs ga8 $w , 0209u asf ff bb j t c rr ll ff as s v x".split(" ");
        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap worldMap = new RectangularMap(6,6);
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

        List<Vector2d> correctPositions = List.of(new Vector2d(2,2), new Vector2d(4,4));
        List<MapDirection> correctOrientations = List.of(MapDirection.SOUTH, MapDirection.EAST);

        List<Vector2d> actualPositions = new ArrayList<>();
        List<MapDirection> actualOrientations = new ArrayList<>();

        List<Animal> animals = simulation.getAnimalsList();
        for (Animal animal : animals) {
            actualPositions.add(animal.getPosition());
            actualOrientations.add(animal.getOrientation());
        }

        assertEquals(correctPositions, actualPositions);
        assertEquals(correctOrientations, actualOrientations);
    }

    @Test
    public void integrationTestNoAnimals() { // test if not correct input is eliminated
        String[] input = "l r l f fafs ga8 $w , 0209u asf ff bb j t c rr ll ff as s v x".split(" ");
        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
        List<Vector2d> positions = List.of();
        WorldMap worldMap = new RectangularMap(6,6);
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulation.run();

        assertTrue(simulation.getAnimalsList().isEmpty());
    }
}

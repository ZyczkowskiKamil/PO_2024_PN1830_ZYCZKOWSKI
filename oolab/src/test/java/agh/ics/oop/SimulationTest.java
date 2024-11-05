package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {

    // old tests
//    @Test
//    public void integrationTest1() {
//        String[] input = "l r l f".split(" ");
//        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
//        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//        Simulation simulation = new Simulation(positions, directions);
//        simulation.run();
//
//        Animal animal0 = new Animal(2,2);
//        animal0.move(MoveDirection.LEFT);
//        animal0.move(MoveDirection.LEFT);
//        Animal animal1 = new Animal(3,4);
//        animal1.move(MoveDirection.RIGHT);
//        animal1.move(MoveDirection.FORWARD);
//
//        assertEquals(animal0.toString(), simulation.getAnimalsList().get(0).toString());
//        assertEquals(animal1.toString(), simulation.getAnimalsList().get(1).toString());
//    }
//
//    @Test
//    public void integrationTest2() {
//        String[] input = "f b r l f f r r f f f f f f f f".split(" ");
//        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
//        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//        Simulation simulation = new Simulation(positions, directions);
//        simulation.run();
//
//        Animal animal0 = new Animal(3,0); // (3,0) poludnie
//        animal0.move(MoveDirection.LEFT);
//        animal0.move(MoveDirection.LEFT);
//        Animal animal1 = new Animal(2,4); // (2,4) polnoc
//
//        assertEquals(animal0.toString(), simulation.getAnimalsList().get(0).toString());
//        assertEquals(animal1.toString(), simulation.getAnimalsList().get(1).toString());
//    }
//
//    @Test
//    public void integrationTest3() { // test if not correct input is eliminated
//        String[] input = "l r l f fafs ga8 $w , 0209u asf ff bb j t c rr ll ff as s v x".split(" ");
//        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
//        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
//        Simulation simulation = new Simulation(positions, directions);
//        simulation.run();
//
//        Animal animal0 = new Animal(2,2);
//        animal0.move(MoveDirection.LEFT);
//        animal0.move(MoveDirection.LEFT);
//        Animal animal1 = new Animal(3,4);
//        animal1.move(MoveDirection.RIGHT);
//        animal1.move(MoveDirection.FORWARD);
//
//        assertEquals(animal0.toString(), simulation.getAnimalsList().get(0).toString());
//        assertEquals(animal1.toString(), simulation.getAnimalsList().get(1).toString());
//    }
//
//    @Test
//    public void integrationTestNoAnimals() { // test if not correct input is eliminated
//        String[] input = "l r l f fafs ga8 $w , 0209u asf ff bb j t c rr ll ff as s v x".split(" ");
//        List<MoveDirection> directions = OptionsParser.parseStringToMoveDirection(input);
//        List<Vector2d> positions = List.of();
//        Simulation simulation = new Simulation(positions, directions);
//        simulation.run();
//
//        assertTrue(simulation.getAnimalsList().isEmpty());
//    }
}

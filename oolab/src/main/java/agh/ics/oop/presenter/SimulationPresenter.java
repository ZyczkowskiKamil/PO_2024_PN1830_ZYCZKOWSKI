package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;

    @FXML
    private Label infoLabel;
    
    @FXML
    private Label moveInfoLabel;
    
    @FXML
    private TextField movesListTextField;
    
    @FXML
    private Button startSimulationButton;

    @FXML
    private GridPane mapGrid;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        startSimulation();
    }

    public void startSimulation() {
        try {
            List<MoveDirection> movesList = getMoveDirectionsListFromTextField();
            List<Vector2d> animalPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));

            Simulation simulation = new Simulation(animalPositions, movesList, this.map);
            List<Simulation> simulationsList = new ArrayList<>(List.of(simulation));
            SimulationEngine simulationEngine = new SimulationEngine(simulationsList);

            simulationEngine.runAsync();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawMap() {
//        clearGrid();
//        Boundary mapBoundaries = this.map.getCurrentBounds();
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            System.out.println("Map changed: " + message);
            this.moveInfoLabel.setText(message);
            this.drawMap();
        });
    }

    private List<MoveDirection> getMoveDirectionsListFromTextField() {
        List<MoveDirection> directions = new LinkedList<>();
        try {
            directions = OptionsParser.parseStringToMoveDirection(movesListTextField.getText().split(" "));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Illegal argument in TextField while parsing to MoveDirection");
        }
        return directions;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

}

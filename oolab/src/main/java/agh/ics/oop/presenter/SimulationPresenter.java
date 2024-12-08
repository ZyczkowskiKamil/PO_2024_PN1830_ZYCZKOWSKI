package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;

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
        this.worldMap = map;
    }

    public void onSimulationStartClicked() {
        startSimulation();
    }

    public void startSimulation() {
        try {
            List<MoveDirection> movesList = getMoveDirectionsListFromTextField();
            List<Vector2d> animalPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));

            Simulation simulation = new Simulation(animalPositions, movesList, this.worldMap);
            List<Simulation> simulationsList = new ArrayList<>(List.of(simulation));
            SimulationEngine simulationEngine = new SimulationEngine(simulationsList);

            simulationEngine.runAsync();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawMap() {
        clearGrid();
        Boundary mapBoundaries = worldMap.getCurrentBounds();
        int columns = mapBoundaries.upperRight().getX()-mapBoundaries.bottomLeft().getX();
        int rows = mapBoundaries.upperRight().getY()-mapBoundaries.bottomLeft().getY();
        int offsetX = mapBoundaries.bottomLeft().getX();
        int offsetY = mapBoundaries.bottomLeft().getY();
        Text label = new Text("y/x");
        mapGrid.setHalignment(label, HPos.CENTER);
        mapGrid.add(label, 0, 0);

        for(int i=0; i<rows+1; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(30));
            Text coordinateY = new Text(Integer.toString(i+offsetY));
            mapGrid.setHalignment(coordinateY, HPos.CENTER);
            mapGrid.add(coordinateY, 0, i+1);
        }
        for(int i=0; i<columns+1; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(30));
            Text coordinateX = new Text(Integer.toString(i+offsetX));
            mapGrid.setHalignment(coordinateX, HPos.CENTER);
            mapGrid.add(coordinateX, i+1, 0);
        }
        mapGrid.getRowConstraints().add(new RowConstraints(30));
        mapGrid.getColumnConstraints().add(new ColumnConstraints(30));
        for(WorldElement el : worldMap.getElements()) {
            Text worldElement = new Text(el.toString());
            mapGrid.setHalignment(worldElement, HPos.CENTER);
            mapGrid.add(worldElement, el.getPosition().getX()+1-offsetX, el.getPosition().getY()+1-offsetY);
        }

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

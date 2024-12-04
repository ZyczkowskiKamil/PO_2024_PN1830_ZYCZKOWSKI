package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void onSimulationStartClicked() {
        List<MoveDirection> directions = getMovesFromTextField();
        
    }

    public void drawMap() {
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Map changed: " + message);
        this.moveInfoLabel.setText(message);
        this.drawMap();
    }

    private List<MoveDirection> getMovesFromTextField() {
        List<MoveDirection> directions = new LinkedList<>();
        try {
            directions = OptionsParser.parseStringToMoveDirection(movesListTextField.getText().split(" "));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Illegal argument in TextField while parsing to MoveDirection");
            e.printStackTrace();
        }
        return directions;
    }


}

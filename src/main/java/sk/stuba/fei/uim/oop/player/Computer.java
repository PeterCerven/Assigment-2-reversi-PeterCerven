package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.game.GameLogic;
import sk.stuba.fei.uim.oop.game.board.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Computer {
    private ArrayList<ArrayList<Tile>> valueY;
    private GameLogic gameLogic;

    public Computer(ArrayList<ArrayList<Tile>> valueY, GameLogic gameLogic) {
        this.valueY = valueY;
        this.gameLogic = gameLogic;
    }

    public void computerTurn(){
        gameLogic.createPossiblePlacements(Color.WHITE, Color.BLACK, true, true);
        ArrayList<Point> possiblePlaces = new ArrayList<>();
        int counter = 0;
        for(ArrayList<Tile> tiles: valueY){
            for (Tile tile : tiles){
                if (tile.isCanBeTaken()){
                    possiblePlaces.add(new Point(tile.getXb(),tile.getYb()));
                    counter++;
                }
            }
        }
        if (counter == 0){
            System.exit(-1);
        }
        Random random = new Random();
        int chosenNum = random.nextInt(counter);
        int column = possiblePlaces.get(chosenNum).y;
        int row = possiblePlaces.get(chosenNum).x;
        valueY.get(column).get(row).setTaken(valueY.get(column).get(row).getToBeOwned());
        gameLogic.clearPossiblePlacements();
        gameLogic.findOppositeColor(column, row, Color.BLACK, Color.WHITE);
        gameLogic.createPossiblePlacements(Color.BLACK, Color.WHITE, true, true);
    }
}

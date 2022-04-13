package sk.stuba.fei.uim.oop.game;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.game.menu.MyJLabel;
import sk.stuba.fei.uim.oop.game.menu.StoneCountText;


import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

@Getter
@Setter
public class GameLogic {
    private ArrayList<ArrayList<Tile>> valueY;
    private StoneCountText blackCount;
    private StoneCountText whiteCount;
    private Game game;
    private MyJLabel myJLabel;


    public GameLogic(ArrayList<ArrayList<Tile>> valueY , StoneCountText blackCount, StoneCountText whiteCount, Game game) {
        this.valueY = valueY;
        this.blackCount = blackCount;
        this.whiteCount = whiteCount;
        this.game = game;

    }

    public void clearPossiblePlacements() {
        for (ArrayList<Tile> tiles : this.valueY) {
            for (Tile tile : tiles) {
                if (tile.isCanBeTaken()) {
                    tile.setCanBeTaken(false);
                    tile.getStone().repaint(tile.currentColor);
                }
            }
        }
    }

    private int countEnemyTiles(int x, int y){
        int tempRow;
        int tempColumn;
        int counter;
        int max = 0;
        for (int rows = -1; rows <= 1; rows++) {
            INNER:
            for (int columns = -1; columns <= 1; columns++) {
                counter = 0;
                tempRow = rows;
                tempColumn = columns;
                if (tempRow == 0 && tempColumn == 0) {
                    continue;
                }
                if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)) {
                    continue;
                }
                while (valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(BLACK)){
                    tempRow += rows;
                    tempColumn += columns;
                    counter++;
                    if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)){
                        continue INNER;
                    }
                }
                if (valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(WHITE)){
                    max += counter;
                }
            }
        }
        return max;
    }

    private Point chooseBest(){
        Point best = new Point();
        int count = 0;
        int max = 0;
        boolean exist = false;
        for(ArrayList<Tile> tiles: valueY){
            for (Tile tile : tiles){
                if (tile.isCanBeTaken() && tile.getToBeOwned().equals(WHITE)){
                    count = countEnemyTiles(tile.getXb(), tile.getYb());
                    if (count > max) {
                        exist = true;
                        max = count;
                        best.setLocation(tile.getXb(), tile.getYb());
                    }
                }
            }
        }
        if (exist) {
            return best;
        }
        return null;


    }

    public void computerTurn(){
        createPossiblePlacements(WHITE, BLACK, false);
        Point best = chooseBest();
        if (best == null){
            return;
        }
        valueY.get(best.y).get(best.x).setTaken(valueY.get(best.y).get(best.x).getToBeOwned());
        clearPossiblePlacements();
        replaceStones(best.y, best.x, BLACK, WHITE);
        createPossiblePlacements(BLACK, WHITE, false);
    }

    public void createPossiblePlacements(Color myColor, Color enemyColor, boolean checkEndGame) {
        ArrayList<Point> neighbours = new ArrayList<>();
        boolean canPlayTurn = false;
        for (int i = 0; i < valueY.size(); i++) {
            for (int j = 0; j < valueY.get(i).size(); j++) {
                if (valueY.get(i).get(j).getCurrentColor().equals(myColor)) {
                    neighbours = findNeighbours(i, j, enemyColor);
                    if (!neighbours.isEmpty()) {
                        canPlayTurn = true;
                        for (Point points : neighbours) {
                            valueY.get(points.y).get(points.x).setCandidate(myColor);
                        }
                    }
                }
            }
        }
        if (checkEndGame && !canPlayTurn){
            if (getNumStones(WHITE) > getNumStones(BLACK)){
                    game.getLabelWinner().showWinner( "White has won");
                } else {
                    game.getLabelWinner().showWinner( "Black has won");
                }
            canPlayTurn = true;
        }

        if (!canPlayTurn) {
            if (myColor.equals(BLACK)){
                computerTurn();
            }
            if (myColor.equals(WHITE)){
                createPossiblePlacements(BLACK, WHITE, true);
            }
        }
    }

    public  ArrayList<Point> findNeighbours(int y, int x, Color enemyColor) {
        boolean enemy;
        int tempRow;
        int tempColumn;
        ArrayList<Point> grey = new ArrayList<>();
        for (int rows = -1; rows <= 1; rows++) {
            INNER:
            for (int columns = -1; columns <= 1; columns++) {
                enemy = false;
                tempRow = rows;
                tempColumn = columns;
                if (tempRow == 0 && tempColumn == 0) {
                    continue;
                }
                if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)) {
                    continue;
                }
                while (valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(enemyColor)){
                    tempRow += rows;
                    tempColumn += columns;
                    enemy = true;
                    if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)){
                        continue INNER;
                    }
                }
                if (!valueY.get(tempColumn + y).get(tempRow + x).isTaken() &&
                        !valueY.get(tempColumn + y).get(tempRow + x).isCanBeTaken() && enemy){
                    grey.add(new Point(tempRow + x, tempColumn + y));
                }
            }
        }
        return grey;
    }




    private int getNumStones(Color color){
        int count = 0;
        for (ArrayList<Tile> tiles : valueY) {
            for (Tile tile: tiles ) {
                if (tile.isTaken() && tile.currentColor.equals(color)){
                    count++;
                }
            }
        }
        return count;
    }





    public boolean isOnMap(int size, int x, int y){
        return x < (size) && x >= 0 && y < size && y >= 0;
    }

    public void replaceStones(int yb, int xb, Color enemyColor, Color myColor) {
        int a;
        int b;
        for (int rows = -1; rows <= 1; rows++) {
            for (int columns = -1; columns <= 1; columns++) {
                a = rows;
                b = columns;
                if (rows == 0 && columns == 0) {
                    continue;
                }
                if (!isOnMap(valueY.size(),a + xb,b + yb)) {
                    continue;
                }
                if (valueY.get(b + yb).get(a + xb).getCurrentColor().equals(enemyColor)) {
                    while (valueY.get(b + yb).get(a + xb).getCurrentColor().equals(enemyColor)) {
                        a += rows;
                        b += columns;
                        if (!isOnMap(valueY.size(),a + xb,b + yb)) {
                            a -= rows;
                            b -= columns;
                            break;
                        }
                    }
                    if (valueY.get(b + yb).get(a + xb).getCurrentColor().equals(myColor)) {
                        while (a != rows || b != columns){
                            valueY.get(b + yb).get(a + xb).setTaken(myColor);
                            a -= rows;
                            b -= columns;
                        }
                        valueY.get(b + yb).get(a + xb).setTaken(myColor);
                    }
                }
            }
        }
        blackCount.ChangeNumberStone(getNumStones(BLACK),"Black");
        whiteCount.ChangeNumberStone(getNumStones(WHITE),"White");
    }
}

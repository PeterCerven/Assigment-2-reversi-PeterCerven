package sk.stuba.fei.uim.oop.game;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.game.menu.StoneCountText;


import java.awt.*;
import java.util.ArrayList;
@Getter
@Setter
public class GameLogic {
    private ArrayList<ArrayList<Tile>> valueY;
    private StoneCountText blackCount;
    private StoneCountText whiteCount;
    private StoneCountText otherCount;

    public GameLogic(ArrayList<ArrayList<Tile>> valueY , StoneCountText blackCount, StoneCountText whiteCount, StoneCountText otherCount) {
        this.valueY = valueY;
        this.blackCount = blackCount;
        this.whiteCount = whiteCount;
        this.otherCount = otherCount;
    }

    public void clearPossiblePlacements() {
        for (ArrayList<Tile> tiles : this.valueY) {
            for (Tile tile : tiles) {
                if (tile.isCanBeTaken()) {
                    tile.setCanBeTaken(false);
                    tile.setBackground(tile.currentColor);
                }
            }
        }
    }
    public void createPossiblePlacements(Color myColor, Color enemyColor, boolean whiteCheck, boolean blackCheck) {
        ArrayList<Point> neighbours = new ArrayList<>();
        boolean check = false;
        for (int i = 0; i < valueY.size(); i++) {
            for (int j = 0; j < valueY.get(i).size(); j++) {
                if (valueY.get(i).get(j).getCurrentColor().equals(enemyColor)) {
                    neighbours = findNeighbours(i, j, myColor, enemyColor);
                    if (!neighbours.isEmpty()) {
                        check = true;
                        if (myColor.equals(Color.WHITE)) {
                            whiteCheck = true;
                        }
                        if (myColor.equals(Color.BLACK)) {
                            blackCheck = true;
                        }
                        for (Point points : neighbours) {
                            valueY.get(points.y).get(points.x).setCandidate(myColor);
                        }
                    }
                }
            }
        }
        if (!check) {
            if (myColor.equals(Color.WHITE)) {
                whiteCheck = false;
            }
            if (myColor.equals(Color.BLACK)) {
                blackCheck = false;
            }
            if (!whiteCheck && !blackCheck) {
                if (getWhiteNumbers() > getBlackNumbers()){
                    System.out.println("White won");
                } else {
                    System.out.println("Black won");
                }
            } else {
                createPossiblePlacements(enemyColor, myColor, whiteCheck, blackCheck);
            }
        }
    }

    public  ArrayList<Point> findNeighbours(int y, int x, Color myColor, Color enemyColor) {
        int tempX;
        int tempY;
        int tempRow;
        int tempColumn;
        ArrayList<Point> grey = new ArrayList<>();
        for (int rows = -1; rows <= 1; rows++) {
            INNER:
            for (int columns = -1; columns <= 1; columns++) {
                tempX = rows;
                tempY = columns;
                tempRow = rows;
                tempColumn = columns;
                if (tempRow == 0 && tempColumn == 0) {
                    continue;
                }
                if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)) {
                    continue;
                }
                do {
                    if (valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(myColor)){
                        tempRow -= tempX;
                        tempColumn -= tempY;
                        while(valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(enemyColor)){
                            tempRow -= tempX;
                            tempColumn -= tempY;
                            if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)){
                                continue INNER;
                            }
                        }
                        if (!(valueY.get(tempColumn + y).get(tempRow + x).isTaken()) ){
                            grey.add(new Point(tempRow + x, tempColumn + y));
                            continue INNER;
                        }

                    }
                    if (!(valueY.get(tempColumn + y).get(tempRow + x).isTaken())){
                        int candidateX = tempRow + x;
                        int candidateY = tempColumn + y;
                        tempRow -= tempX;
                        tempColumn -= tempY;
                        while(valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(enemyColor)){
                            tempRow -= tempX;
                            tempColumn -= tempY;
                            if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)){
                                continue INNER;
                            }
                        }
                        if (valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(myColor)){
                            grey.add(new Point(candidateX, candidateY));
                            continue INNER;
                        }
                    }
                    tempRow += tempX;
                    tempColumn += tempY;
                    if (!isOnMap(valueY.size(), tempRow + x, tempColumn + y)){
                        continue INNER;
                    }
                } while (valueY.get(tempColumn + y).get(tempRow + x).getCurrentColor().equals(enemyColor));
            }
        }
        return grey;
    }

    public int getWhiteNumbers(){
        int count = 0;
        System.out.println("---------------------------------------------------------");
        for (ArrayList<Tile> tiles : valueY) {
            System.out.println();
            for (Tile tile: tiles ) {
                System.out.println(tile.currentColor + " [" + tile.getXb() +"]" +  "[" +tile.getYb() + "]");
                if (tile.isTaken() && tile.currentColor.equals(Color.WHITE)){
                    count++;
                }
            }
        }
        return count;
    }


    public int getOtherNumbers(){
        int count = 0;
        for (ArrayList<Tile> tiles : valueY) {
            for (Tile tile: tiles ) {
                if (!tile.isTaken()){
                    count++;
                }
            }
        }
        return count;
    }

    public int getBlackNumbers(){
        int count = 0;
        for (ArrayList<Tile> tiles : valueY) {
            for (Tile tile: tiles ) {
                if (tile.isTaken() && tile.currentColor.equals(Color.BLACK)){
                    count++;
                }
            }
        }
        return count;
    }



    public boolean isOnMap(int size, int x, int y){
        if (x >= (size) || x < 0 || y >= size || y < 0){
            return false;
        }
        return true;
    }

    public void findOppositeColor(int yb, int xb, Color enemyColor, Color myColor) {
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
        blackCount.ChangeNumberStone(getBlackNumbers(),"Black");
        whiteCount.ChangeNumberStone(getWhiteNumbers(),"White");
        otherCount.ChangeNumberStone(getOtherNumbers(),"Change");
    }
}

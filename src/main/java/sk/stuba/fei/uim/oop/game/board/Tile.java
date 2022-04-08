package sk.stuba.fei.uim.oop.game.board;


import lombok.Data;
import sk.stuba.fei.uim.oop.utility.MyMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Data
public class Tile extends JPanel {
    private boolean taken;
    public Color currentColor;
    private boolean canBeTaken;
    private Color toBeOwned;
    private MyMouseAdapter myMouseAdapter;
    private ArrayList<ArrayList<Tile>> valueY;
    private int xb;
    private int yb;

    public Tile(Color color, int size, int xb, int yb, ArrayList<ArrayList<Tile>> valueY) {
        super();
        this.toBeOwned = color;
        this.currentColor = color;
        this.setBackground(color);
        this.setBounds(50, 50, size, size);
        this.valueY = valueY;
        this.xb = xb;
        this.yb = yb;
        myMouseAdapter = new MyMouseAdapter( this);
        this.addMouseListener(myMouseAdapter);
    }


    public void setTaken(Color color) {
        this.currentColor = color;
        this.taken = true;
        this.setBackground(color);
        this.canBeTaken = false;
        this.toBeOwned = color;
    }

    public void setCandidate(Color color, ArrayList<ArrayList<Tile>> valueY) {
        this.canBeTaken = true;
        this.setBackground(Color.GRAY);
        this.toBeOwned = color;
    }


    public void clearPossiblePlacements() {
        for (ArrayList<Tile> tiles : this.valueY) {
            for (Tile tile : tiles) {
                if (tile.canBeTaken) {
                    tile.setCanBeTaken(false);
                    tile.setBackground(tile.currentColor);
                }
            }
        }
    }
    public void createPossiblePlacements(Color myColor, Color enemyColor) {
        ArrayList<Point> neighbours = new ArrayList<Point>();
        for (int i = 0; i < valueY.size(); i++) {
            for (int j = 0; j < valueY.get(i).size(); j++) {
                if (valueY.get(i).get(j).getCurrentColor().equals(enemyColor)) {
                    neighbours = findNeighbours(i, j, myColor, enemyColor);
                    if (!neighbours.isEmpty()) {
                        for (Point points : neighbours) {
                            valueY.get(points.y).get(points.x).setCandidate(myColor , valueY);
                        }
                    }
                }
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



    public boolean isOnMap(int size, int x, int y){
        if (x >= (size) || x < 0 || y >= size || y < 0){
            return false;
        }
        return true;
    }

    public void findOppositeColor(Color enemyColor, Color myColor) {
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
                            valueY.get(b + yb).get(a + xb).setBackground(myColor);
                            valueY.get(b + yb).get(a + xb).setCurrentColor(myColor);
                            valueY.get(b + yb).get(a + xb).setToBeOwned(myColor);
                            a -= rows;
                            b -= columns;
                        }
                        valueY.get(b + yb).get(a + xb).setBackground(myColor);
                        valueY.get(b + yb).get(a + xb).setCurrentColor(myColor);
                        valueY.get(b + yb).get(a + xb).setToBeOwned(myColor);
                    }
                }
            }
        }
    }
}




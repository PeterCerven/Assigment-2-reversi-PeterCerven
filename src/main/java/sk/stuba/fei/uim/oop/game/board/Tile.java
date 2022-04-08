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


    public ArrayList<Point> findNeighbours(int y, int x, Color myColor, Color enemyColor) {
        ArrayList<Point> grey = new ArrayList<>();
        int a;
        int b;
        for (int rows = -1; rows <= 1; rows++) {
            logger:
            for (int columns = -1; columns <= 1; columns++) {
                if (rows == 0 && columns == 0) {
                    continue;
                }
                if (!isOnMap(valueY.size(), rows + x, columns + y)) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows +x).isCanBeTaken()) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows +x).getCurrentColor().equals(myColor) && !(valueY.get(y - columns).get(x - rows).getCurrentColor().equals(enemyColor))) {
                    grey.add(new Point(x - rows, y - columns));
                    continue;
                }
                if (valueY.get(columns + y).get(rows +x).getCurrentColor().equals(enemyColor)) {
                    a = rows;
                    b = columns;
                    while (valueY.get(b + y).get(a + x).getCurrentColor().equals(enemyColor)){
                        a += rows ;
                        b += columns;
                        if (!isOnMap(valueY.size(),b + y,a + x)) {
                            continue logger;
                        }
                    }
                    if (!isOnMap(valueY.size(),b + y,a + x)) {
                        break;
                    }
                    if (valueY.get(b + y).get(a + x).getCurrentColor().equals(myColor)){
                        a -= rows;
                        b -= columns;
                        while(isOnMap(valueY.size(), a + x, b + y)){
                            a -= rows;
                            b -= columns;
                            if(!isOnMap(valueY.size(), a + x, b + y)){
                                break;
                            }
                            if (!(valueY.get(b + y).get(a + x).isTaken() || valueY.get(b + y).get(a + x).isCanBeTaken())){
                                grey.add(new Point(a + x, b + y));
                            } else {
                                break;
                            }
                        }
                    } if (!(valueY.get(b + y).get(a + x).isCanBeTaken() && valueY.get(b + y).get(a + x).isTaken())) {
                        int temp_Y = b + y;
                        int temp_X = a + x;
                        while (isOnMap(valueY.size(), a + x, b + y)){
                            if (valueY.get(b + y).get(a + x).getCurrentColor().equals(myColor)){
                                grey.add(new Point(temp_X, temp_Y));
                                break;
                            }
                            a -= rows;
                            b -= columns;
                        }
                    }
//
                }
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
                if (rows == columns) {
                    continue;
                }
                if (valueY.get(columns + yb).get(rows + xb).getCurrentColor().equals(enemyColor)) {
                    a = rows;
                    b = columns;
                    while (valueY.get(b + yb).get(a + xb).getCurrentColor().equals(enemyColor)) {
                        a += rows;
                        b += columns;
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




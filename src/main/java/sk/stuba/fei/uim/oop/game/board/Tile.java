package sk.stuba.fei.uim.oop.game.board;


import lombok.Data;
import sk.stuba.fei.uim.oop.utility.MyMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@Data
public class Tile extends JPanel {
    private boolean taken;
    private Color ownerColor;
    private boolean canBeTaken;
    private Color toBeOwned;
    private JPanel board;
    private MyMouseAdapter myMouseAdapter;
    private ArrayList<ArrayList<Tile>> valueY;
    private int xb;
    private int yb;

    public Tile(Color color, int size, int xb, int yb, ArrayList<ArrayList<Tile>> valueY) {
        super();
        this.ownerColor = color;
        this.setBackground(color);
        this.setBounds(50, 50, size, size);
        this.valueY = valueY;
        this.xb = xb;
        this.yb = yb;
        myMouseAdapter = new MyMouseAdapter( this);
        this.addMouseListener(myMouseAdapter);
    }


    public void setTaken(Color color) {
        this.ownerColor = color;
        this.taken = true;
        this.setBackground(color);
        this.canBeTaken = false;
    }

    public void setCandidate(Color color, ArrayList<ArrayList<Tile>> valueY) {
        this.canBeTaken = true;
        this.setBackground(Color.GRAY);
        this.toBeOwned = color;
        this.valueY = valueY;
    }

    public void setCanBeTaken(Boolean bool) {
        this.canBeTaken = bool;
    }

    public void clearPossiblePlacements() {
        for (ArrayList<Tile> tiles : this.valueY) {
            for (Tile tile : tiles) {
                if (tile.canBeTaken) {
                    tile.setCanBeTaken(false);
                    tile.setBackground(ownerColor);
                    System.out.println(ownerColor);
                }
            }
        }
    }
    public void createPossiblePlacements(Color myColor, Color enemyColor) {
        ArrayList<Point> neighbours = new ArrayList<Point>();
        for (int i = 0; i < valueY.size(); i++) {
            for (int j = 0; j < valueY.get(i).size(); j++) {
                if (valueY.get(i).get(j).getOwnerColor().equals(enemyColor)) {
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

    private ArrayList<Point> findNeighbours(int y, int x, Color myColor, Color enemyColor) {
        ArrayList<Point> grey = new ArrayList<>();
        int a;
        int b;
        for (int rows = -1; rows <= 1; rows++) {
            for (int columns = -1; columns <= 1; columns++) {
                if (rows == columns) {
                    continue;
                }
                if (valueY.size() <= (columns + y) || valueY.get(y).size() <= (rows + x)) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows + x).isCanBeTaken()) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows + x).getOwnerColor().equals(myColor)) {
                    grey.add(new Point(x - rows, y - columns));
                    continue;
                }
                if (valueY.get(columns + y).get(rows + x).getOwnerColor().equals(enemyColor)) {
                    a = rows;
                    b = columns;
                    while (valueY.get(b + y).get(a + x).getOwnerColor().equals(enemyColor)){
                        a += rows ;
                        b += columns;
                    }
                    if (valueY.get(b + y).get(a + x).getOwnerColor().equals(myColor)){
                        grey.add(new Point(x - rows, y - columns));
                    }
                }
            }
        }
        return grey;
    }

    public void findOppositeColor(Color enemyColor, Color myColor) {
        int a;
        int b;
        for (int rows = -1; rows <= 1; rows++) {
            for (int columns = -1; columns <= 1; columns++) {
                if (rows == columns) {
                    continue;
                }
                if (valueY.get(columns + yb).get(rows + xb).getOwnerColor().equals(enemyColor)) {
                    a = rows;
                    b = columns;
                    while (valueY.get(b + yb).get(a + xb).getOwnerColor().equals(enemyColor)) {
                        a += rows;
                        b += columns;
                    }
                    if (valueY.get(b + yb).get(a + xb).getOwnerColor().equals(myColor)) {
                        while (a != rows || b != columns){
                            valueY.get(b + yb).get(a + xb).setBackground(myColor);
                            a -= rows;
                            b -= columns;
                        }
                        valueY.get(b + yb).get(a + xb).setBackground(myColor);
                    }
                }
            }
        }
        createPossiblePlacements(enemyColor, myColor);
    }
}




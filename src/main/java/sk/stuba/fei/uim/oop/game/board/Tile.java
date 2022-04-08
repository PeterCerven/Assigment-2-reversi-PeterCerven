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
        myMouseAdapter = new MyMouseAdapter( this , valueY);
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

}




package sk.stuba.fei.uim.oop.game.board;



import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.game.GameLogic;
import sk.stuba.fei.uim.oop.utility.MyMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class Tile extends JPanel {
    private boolean taken;
    public Color currentColor;
    private boolean canBeTaken;
    private Color toBeOwned;
    private MyMouseAdapter myMouseAdapter;
    private ArrayList<ArrayList<Tile>> valueY;
    private boolean white;
    private boolean black;
    private int xb;
    private int yb;
    private Stone stone;


    public Tile(Color color, int size, int xb, int yb, ArrayList<ArrayList<Tile>> valueY, GameLogic gameLogic) {
        super();
        this.toBeOwned = color;
        this.currentColor = color;
        this.setBackground(color);
        this.setBounds(0, 0, size, size);
        this.valueY = valueY;
        this.xb = xb;
        this.yb = yb;
        Stone stone = new Stone(size, size, Color.BLACK);
        this.stone = stone;
        this.add(stone, BorderLayout.CENTER);


        myMouseAdapter = new MyMouseAdapter( this , valueY, gameLogic);
        this.addMouseListener(myMouseAdapter);
    }


    public void setTaken(Color color) {
        this.currentColor = color;
        this.taken = true;
        this.setBackground(color);
        this.canBeTaken = false;
        this.toBeOwned = color;
    }

    public void setCandidate(Color color) {
        this.canBeTaken = true;
        this.setBackground(Color.GRAY);
        this.toBeOwned = color;
    }

}




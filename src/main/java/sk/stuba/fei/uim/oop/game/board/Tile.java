package sk.stuba.fei.uim.oop.game.board;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private boolean taken;
    private Color ownerColor = Color.YELLOW;
    private boolean canBeTaken;

    public Tile(Color color, int width, int height, int size) {
        this.setBackground(color);
        this.setBounds(0, 0, size, size);
    }

    public void setTaken(Color color){
        this.ownerColor = color;
        taken = true;
        this.setBackground(color);
    }

    public void setCanditate(){
        this.canBeTaken = true;
        setBackground(Color.GRAY);
    }

    public boolean isTaken() {
        return taken;
    }

    public boolean isCanBeTaken(){
        return canBeTaken;
    }

    public Color getOwnerColor() {
        return ownerColor;
    }
}

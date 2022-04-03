package sk.stuba.fei.uim.oop.game.board;

import javax.swing.*;
import java.awt.*;

public abstract class MyPanel extends JPanel {
    protected Color color;
    protected int x;
    protected int y;
    public MyPanel(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
}

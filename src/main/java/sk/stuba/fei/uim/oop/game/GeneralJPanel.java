package sk.stuba.fei.uim.oop.game;

import javax.swing.*;
import java.awt.*;

public class GeneralJPanel extends JPanel {
    protected Color color;
    protected int x;
    protected int y;

    public GeneralJPanel(Color color, int x, int y) {
        super();
        this.color = color;
        this.x = x;
        this.y = y;
    }
}

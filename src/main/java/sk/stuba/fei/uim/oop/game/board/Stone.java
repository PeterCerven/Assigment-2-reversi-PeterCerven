package sk.stuba.fei.uim.oop.game.board;

import javax.swing.*;
import java.awt.*;

public class Stone extends Canvas{
    private Color color;
    private Graphics g;



    public Stone(Color color) {
        this.color = color;
        this.setPreferredSize(new Dimension(50, 50));
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(this.color);
        g.fillOval(0,0,50,50);
    }
}

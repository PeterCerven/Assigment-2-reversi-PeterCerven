package sk.stuba.fei.uim.oop.game.board;

import javax.swing.*;
import java.awt.*;

public class Stone extends JPanel {
    private int width;
    private int height;
    private Color color;

    public Stone(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(this.width,this.height));
    }


    @Override
    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(this.color);
        g2D.setStroke(new BasicStroke(5));
        g2D.fillOval(this.width/8,this.height/12,this.width/4 * 3,this.height/4 * 3);

    }
}

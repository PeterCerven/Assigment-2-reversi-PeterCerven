package sk.stuba.fei.uim.oop.game.board;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Setter
public class Stone extends JPanel {
    private int width;
    private int height;
    private Color color;

    public Stone(int width, int height, Color color) {
        super();
        this.width = width;
        this.height = height;
        this.color = color;
        this.setPreferredSize(new Dimension(this.width,this.height));
        this.setOpaque(false);
    }

    public void repaint(Color color){
        this.setColor(color);
        revalidate();
        repaint();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setPaint(this.color);
        g2D.setStroke(new BasicStroke(5));
        if (color.equals(Color.GRAY)) {
            g2D.drawOval(this.width/8,this.height/12,this.width/4 * 3,this.height/4 * 3);
        } else {
            g2D.fillOval(this.width/8,this.height/12,this.width/4 * 3,this.height/4 * 3);
        }


    }
}

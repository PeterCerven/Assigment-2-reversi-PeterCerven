package sk.stuba.fei.uim.oop.game.board;

import java.awt.*;

public class Menu extends MyPanel {
    public Menu(Color color, int width, int height) {
        super(color, width, height);
        this.setBackground(color);
        this.setPreferredSize(new Dimension(width,height));
    }
}

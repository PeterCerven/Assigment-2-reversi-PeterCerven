package sk.stuba.fei.uim.oop.game.board;

import javax.swing.*;
import java.awt.*;

public class Tile extends MyPanel {
    public Tile(Color color, int width, int height, int size) {
        super(color, width, height);
        this.setBackground(color);
        this.setBounds(0, 0, size, size);
    }
}

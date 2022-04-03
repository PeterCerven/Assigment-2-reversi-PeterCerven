package sk.stuba.fei.uim.oop.game.board;

import java.awt.*;

public class Board extends MyPanel {
    public Board(Color color, int width, int height) {
        super(color, width, height);
        this.setBackground(color);
        this.setBounds(0, 0, width, height);
    }


}

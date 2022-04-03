package sk.stuba.fei.uim.oop.game.board;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    public Board(Color color, int x, int y, int width, int height) {
        super();
        this.setBackground(color);
        this.setBounds(x, y, width, height);

    }
}

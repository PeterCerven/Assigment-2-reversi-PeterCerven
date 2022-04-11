package sk.stuba.fei.uim.oop.game.menu;

import sk.stuba.fei.uim.oop.game.board.MyPanel;

import javax.swing.*;
import java.awt.*;

public class Menu extends MyPanel {
    public Menu(Color color, int width, int height) {
        super(color, width, height);
        this.setBackground(color);
        this.setPreferredSize(new Dimension(width,height));
        JLabel playerTurn = new JLabel("This is Black's player turn!");
        this.add(playerTurn);
        playerTurn.setLocation(50, height -10);
    }
}

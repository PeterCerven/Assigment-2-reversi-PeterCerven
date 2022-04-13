package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.game.menu.ResizeGameComboBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyComboBox implements ActionListener {
    private final ResizeGameComboBox resizeGameComboBox;
    private final Game game;

    public MyComboBox(ResizeGameComboBox resizeGameComboBox, Game game) {
        this.resizeGameComboBox = resizeGameComboBox;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox){
            int size = Integer.parseInt(String.valueOf(((JComboBox<String>) e.getSource()).getSelectedItem()));
            game.restartGame(size);
            resizeGameComboBox.setFocusable(false);
        }
    }
}

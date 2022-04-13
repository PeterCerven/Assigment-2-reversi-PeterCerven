package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.game.menu.ResizeGameComboBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyComboBox implements ItemListener {
    private final ResizeGameComboBox resizeGameComboBox;
    private final Game game;

    public MyComboBox(ResizeGameComboBox resizeGameComboBox, Game game) {
        this.resizeGameComboBox = resizeGameComboBox;
        this.game = game;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof JComboBox){
            int size = Integer.parseInt(String.valueOf(e.getItem()));
            game.restartGame(size);
            resizeGameComboBox.setFocusable(false);
        }
    }
}

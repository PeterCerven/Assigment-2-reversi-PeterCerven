package sk.stuba.fei.uim.oop.game.menu;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.utility.MyComboBox;

import javax.swing.*;

public class ResizeGameComboBox extends JComboBox<Integer> {

    public ResizeGameComboBox(Integer[] items, Game game) {
        super(items);
        MyComboBox myComboBox = new MyComboBox(this, game);
        this.addItemListener(myComboBox);
    }
}

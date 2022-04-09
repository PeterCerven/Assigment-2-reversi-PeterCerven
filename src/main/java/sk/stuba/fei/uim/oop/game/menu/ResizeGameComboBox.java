package sk.stuba.fei.uim.oop.game.menu;

import javax.swing.*;
import java.util.Vector;

public class ResizeGameComboBox extends JComboBox<String> {
        private String items;

    public ResizeGameComboBox(String[] items, String items1) {
        super(items);
        this.items = items1;
    }
}

package sk.stuba.fei.uim.oop.game.menu;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {
    public MyJLabel() {
        this.setSize(50,50);
        this.setVisible(true);
    }


    public void showWinner(String text){
        this.setText(text);
    }
}

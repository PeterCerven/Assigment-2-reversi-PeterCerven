package sk.stuba.fei.uim.oop.game.menu;

import javax.swing.*;

public class MyJLabel extends JLabel {
    private String text;
    public MyJLabel(String text) {
        this.setSize(50, 50);
        this.text = text;
    }

    public void showSize(int num) {
        this.setText(text + "" + num + "x" + num);
    }


    public void showWinner(String text) {
        this.setText(text);
    }
}

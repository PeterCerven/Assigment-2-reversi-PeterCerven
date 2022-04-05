package sk.stuba.fei.uim.oop.game.menu;

import javax.swing.*;
import java.awt.*;

public class ResetButton extends JButton {
    public ResetButton() {
        this.setText("Reset");
        this.setBounds(200,300,100,50);
        this.setFocusable(false);
        this.setFont(new Font("Comic Sans", Font.BOLD,25));
        this.setForeground(Color.CYAN);
        this.setBackground(Color.BLACK);
        //this.addActionListener(actionListener);
    }

}

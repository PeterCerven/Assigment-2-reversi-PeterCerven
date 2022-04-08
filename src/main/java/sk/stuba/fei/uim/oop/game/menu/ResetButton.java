package sk.stuba.fei.uim.oop.game.menu;

import sk.stuba.fei.uim.oop.utility.MyMouseAdapter;
import sk.stuba.fei.uim.oop.utility.ResetGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButton extends JButton{
    private ResetGame resetGame;

    public ResetButton() {
        this.setText("Reset");
        this.setBounds(200,300,100,50);
        this.setFocusable(false);
        this.setFont(new Font("Comic Sans", Font.BOLD,25));
        this.setForeground(Color.CYAN);
        this.setBackground(Color.BLACK);
        this.resetGame = new ResetGame();
        this.addActionListener(resetGame);
    }


}

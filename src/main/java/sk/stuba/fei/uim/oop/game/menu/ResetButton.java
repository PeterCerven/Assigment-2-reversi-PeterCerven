package sk.stuba.fei.uim.oop.game.menu;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.utility.ResetGame;

import javax.swing.*;
import java.awt.*;


public class ResetButton extends JButton {

    public ResetButton(Game game) {
        super();
        this.setText("Reset");
        this.setBounds(200, 300, 100, 50);
        this.setFocusable(false);
        this.setFont(new Font("Comic Sans", Font.BOLD, 25));
        this.setForeground(Color.CYAN);
        this.setBackground(Color.BLACK);
        ResetGame resetGame = new ResetGame(game);
        this.addActionListener(resetGame);
    }


}

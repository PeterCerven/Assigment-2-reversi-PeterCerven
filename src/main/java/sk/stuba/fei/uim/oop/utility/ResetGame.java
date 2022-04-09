package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetGame implements ActionListener {
    private Game game;
    public ResetGame(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.restartGame(game.getCurrentSize());
    }
}

package sk.stuba.fei.uim.oop.utility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import sk.stuba.fei.uim.oop.game.Game;

public class MyKeyAdapter extends KeyAdapter {
    private final Game game;

    public MyKeyAdapter(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'r') {
            game.restartGame(game.getCurrentSize());
        }
        if (e.getKeyCode() == 27) {
            game.gameClose();
        }
    }
}

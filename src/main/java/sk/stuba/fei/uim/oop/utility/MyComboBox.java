package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.game.menu.ResizeGameComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MyComboBox implements ActionListener {
    private final ResizeGameComboBox resizeGameComboBox;
    private final Game game;

    public MyComboBox(ResizeGameComboBox resizeGameComboBox, Game game) {
        this.resizeGameComboBox = resizeGameComboBox;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.resizeGameComboBox){
            switch(Objects.requireNonNull(resizeGameComboBox.getSelectedItem()).toString()){
                case "6x6":
                    game.restartGame(6);
                    break;
                case "8x8":
                    game.restartGame(8);
                    break;
                case "10x10":
                    game.restartGame(10);
                    break;
                case "12x12":
                    game.restartGame(12);
                    break;
            }
            resizeGameComboBox.setFocusable(false);
        }
    }
}

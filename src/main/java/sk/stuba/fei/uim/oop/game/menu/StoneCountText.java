package sk.stuba.fei.uim.oop.game.menu;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class StoneCountText extends JLabel {
    private int countStone;

    public StoneCountText(String text, int countStone) {
        super(text + " " + countStone);
        this.countStone = countStone;
    }

    public void ChangeNumberStone(int number, String color){
        this.countStone = number;
        this.setText(color + " is: " + this.countStone);
    }
}

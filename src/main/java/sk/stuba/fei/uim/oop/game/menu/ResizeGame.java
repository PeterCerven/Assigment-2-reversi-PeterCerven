package sk.stuba.fei.uim.oop.game.menu;

import javax.swing.*;
import java.util.Hashtable;

public class ResizeGame extends JSlider {
    public ResizeGame(int orientation, int min, int max, int value) {
        super(orientation, min, max, value);
        this.setMajorTickSpacing(2);
        this.setMinorTickSpacing(2);
        this.snapToTicks(true);
        this.setPaintTicks(true);
        this.setPaintLabels(true);

    }

    private void snapToTicks(boolean b) {
    }

    @Override
    protected void fireStateChanged() {
        super.fireStateChanged();
    }


}

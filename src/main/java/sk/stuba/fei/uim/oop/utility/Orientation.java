package sk.stuba.fei.uim.oop.utility;

import java.awt.*;

public enum Orientation {
    NORTH(Color.red, 500, 100), WEST(Color.green, 50, 400), EAST(Color.green, 50, 400),
    SOUTH(Color.green, 500, 50), CENTER(Color.blue, 400, 400);

    Color color;
    int x;
    int y;

    Orientation(Color color, int x, int y) {

    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

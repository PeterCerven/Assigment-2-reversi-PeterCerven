package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.board.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyMouseAdapter extends MouseAdapter {
    private Tile tile;

    public MyMouseAdapter( Tile tile) {
        this.tile = tile;
    }





    @Override
    public void mouseClicked(MouseEvent e) {
        if(tile.isCanBeTaken()){
            tile.setBackground(tile.getToBeOwned());
            tile.setCanBeTaken(false);
            tile.clearPossiblePlacements();
            tile.findOppositeColor(Color.BLACK ,Color.WHITE);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(tile.isCanBeTaken()){
            tile.setBackground(Color.DARK_GRAY);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(tile.isCanBeTaken()){
            tile.setBackground(Color.GRAY);
        }
    }
}

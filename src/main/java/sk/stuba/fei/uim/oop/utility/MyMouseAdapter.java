package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.board.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyMouseAdapter extends MouseAdapter {
    private Tile tile;
    private Color me;
    private Color enemy;

    public MyMouseAdapter( Tile tile) {
        this.tile = tile;
    }

    private Color getEnemyColor(Color me){
        if (me.equals(Color.WHITE)){
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if(tile.isCanBeTaken()){
            tile.setTaken(tile.getToBeOwned());
            me = tile.currentColor;
            enemy = getEnemyColor(me);
            tile.clearPossiblePlacements();
            tile.findOppositeColor(enemy, me);
            tile.createPossiblePlacements(enemy, me);
        }
        System.out.println(tile.currentColor);
        System.out.println(tile.isTaken());
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

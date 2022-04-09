package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.GameLogic;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.game.menu.StoneCountText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyMouseAdapter extends MouseAdapter {
    private Tile tile;
    private Color me;
    private Color enemy;
    private GameLogic gameLogic;
    private ArrayList<ArrayList<Tile>> valueY;



    public MyMouseAdapter(Tile tile, ArrayList<ArrayList<Tile>> valueY, GameLogic gameLogic) {
        this.tile = tile;
        this.valueY = valueY;
        this.gameLogic = gameLogic;

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
            gameLogic.clearPossiblePlacements();
            gameLogic.findOppositeColor(tile.getYb(), tile.getXb(), enemy, me);
            gameLogic.createPossiblePlacements(enemy, me, true, true);
        }
//        System.out.println(tile.currentColor);
//        System.out.println(tile.isTaken());
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

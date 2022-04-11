package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.GameLogic;
import sk.stuba.fei.uim.oop.game.board.Tile;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {
    private final Tile tile;
    private final GameLogic gameLogic;



    public MyMouseAdapter(Tile tile, GameLogic gameLogic) {
        this.tile = tile;
        this.gameLogic = gameLogic;

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(tile.isCanBeTaken() && tile.getToBeOwned().equals(Color.BLACK)){
            tile.setTaken(tile.getToBeOwned());
            gameLogic.clearPossiblePlacements();
            gameLogic.replaceStones(tile.getYb(), tile.getXb(), Color.WHITE, Color.BLACK);
            gameLogic.computerTurn();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(tile.isCanBeTaken() && tile.getToBeOwned().equals(Color.BLACK)){
            tile.getStone().repaint(tile.getToBeOwned());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(tile.isCanBeTaken()){
            tile.getStone().repaint(Color.GRAY);
        }
    }
}

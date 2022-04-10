package sk.stuba.fei.uim.oop.utility;

import sk.stuba.fei.uim.oop.game.GameLogic;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.player.Computer;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MyMouseAdapter extends MouseAdapter {
    private Tile tile;
    private GameLogic gameLogic;
    private Computer computer;



    public MyMouseAdapter(Tile tile, ArrayList<ArrayList<Tile>> valueY, GameLogic gameLogic) {
        this.tile = tile;
        this.gameLogic = gameLogic;
        this.computer = new Computer(valueY, gameLogic);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(tile.isCanBeTaken() && tile.getToBeOwned().equals(Color.BLACK)){
            tile.setTaken(tile.getToBeOwned());
            gameLogic.clearPossiblePlacements();
            gameLogic.findOppositeColor(tile.getYb(), tile.getXb(), Color.WHITE, Color.BLACK);
            computer.computerTurn();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(tile.isCanBeTaken() && tile.getToBeOwned().equals(Color.BLACK)){
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

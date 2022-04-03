package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.game.board.Board;
import sk.stuba.fei.uim.oop.game.board.Menu;
import sk.stuba.fei.uim.oop.game.board.MyPanel;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.utility.Orientation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Game extends JFrame implements KeyListener, MouseListener {
    public Game() throws HeadlessException {
        super();

        BufferedImage pic = null;
        try {
            pic = ImageIO.read(Objects.requireNonNull(Game.class.getResourceAsStream("/logo.jpg")));
            ImageIcon icon = new ImageIcon(pic);
            this.setIconImage(pic);
        } catch (IOException | NullPointerException e) {
            System.out.println("uspech");
            e.printStackTrace();
        }
        this.setSize(600, 600);
        this.setTitle("Reversi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x1F7F25));
        Board board = new Board(Color.black, 500, 500);
        Menu menu = new Menu(Color.red, 500, 100);
        createBoardSize(36,10, board);


        this.add(menu,BorderLayout.PAGE_START);
        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);

    }


    private void createBoardSize(int number, int size, Board board){
        for (int i = 0; i < number; i++){
            board.setLayout(new GridLayout(6,6,1,1));
            board.add(new Tile(new Color((i % 2) * 25 ,(i % 2 + 1) * 70,0),0,0, size));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

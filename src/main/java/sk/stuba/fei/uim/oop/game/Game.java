package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.game.board.Board;
import sk.stuba.fei.uim.oop.game.menu.Menu;
import sk.stuba.fei.uim.oop.game.board.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Game extends JFrame{
    public Game() throws HeadlessException {
        super();
        this.setTitle("Reversi");

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
        Menu menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        createBoardSize(6,10, board);


        this.add(menu,BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);

    }


    private void createBoardSize(int number, int size, Board board){
        board.setLayout(new GridLayout(number,number,1,1));
        for (int i = 0; i < number; i++){
            for (int j = 0; j < number; j++){
                board.add(new Tile(new Color(((i + j) % 2) * 25 ,((i +j) % 2 + 1) * 70,0),0,0, size));
            }
        }
    }

}

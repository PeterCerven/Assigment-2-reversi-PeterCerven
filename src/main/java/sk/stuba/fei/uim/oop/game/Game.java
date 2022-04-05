package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.game.board.Board;
import sk.stuba.fei.uim.oop.game.board.Stone;
import sk.stuba.fei.uim.oop.game.menu.Menu;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.game.menu.ResetButton;
import sk.stuba.fei.uim.oop.game.menu.ResizeGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends JFrame{

    public ArrayList<JPanel> s = new ArrayList<>();

    public Game() throws HeadlessException {
        super();
        gameCreate();
        //startGame();
    }

    private void startGame(){

    }

    private void gameCreate(){
        this.setTitle("Reversi");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x1F7F25));
        addImageIcon();
        Board board = new Board(Color.black, 500, 500);
        Menu menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        ResetButton resetButton = new ResetButton();
        ResizeGame resizeGame = new ResizeGame(Adjustable.HORIZONTAL, 6,12,6);

        createBoardSize(6,10, board, s);
        menu.add(resizeGame);
        menu.add(resetButton);
        Stone whiteStone = new Stone(Color.WHITE);
        Stone blackStone = new Stone(Color.BLACK);

        addStone(3,3,blackStone);


        this.add(menu,BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void addStone(int x, int y, Stone stone){
        s.get(4).add(stone);
    }


    private void createBoardSize(int number, int size, Board board, ArrayList<JPanel> s){
        board.setLayout(new GridLayout(number,number,1,1));
        for (int i = 0; i < number; i++){
            for (int j = 0; j < number; j++){
                Tile tile = new Tile(new Color(((i + j) % 2) * 25 ,((i +j) % 2 + 1) * 70,0),0,0, size);
                board.add(tile);
                s.add(tile);
            }
        }
    }

    private void addImageIcon(){
        BufferedImage pic = null;
        try {
            pic = ImageIO.read(Objects.requireNonNull(Game.class.getResourceAsStream("/logo.jpg")));
            ImageIcon icon = new ImageIcon(pic);
            this.setIconImage(pic);
        } catch (IOException | NullPointerException e) {
            System.out.println("uspech");
            e.printStackTrace();
        }
    }

}

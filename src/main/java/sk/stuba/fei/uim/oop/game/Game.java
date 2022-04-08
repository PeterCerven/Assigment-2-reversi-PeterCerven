package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.game.board.Board;
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

public class Game extends JFrame {

    private ArrayList<Tile> valueX = new ArrayList<>();
    private ArrayList<ArrayList<Tile>> valueY = new ArrayList<>();
    private GameLogic gameLogic;

    public Game() throws HeadlessException {
        super();
        gameCreate();
    }

    private void startGame(Color me, Color enemy) {
        gameLogic.createPossiblePlacements(me, enemy);
    }

    private void gameCreate() {
        createFrame();
        addImageIcon();
        int size = createObjects();
        gameLogic = new GameLogic(valueY);
        initialStones(size);
        startGame(Color.BLACK, Color.WHITE);
        this.setVisible(true);

    }
    private void createFrame(){
        this.setTitle("Reversi");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x1F7F25));
        this.addImageIcon();
    }

    public int createObjects(){
        int size = 8;
        Board board = new Board(Color.BLACK, 500, 500);
        Menu menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        ResetButton resetButton = new ResetButton();
        ResizeGame resizeGame = new ResizeGame(Adjustable.HORIZONTAL, 6, 12, size);
        createBoardSize(size, 50, board, valueX, valueY);
        menu.add(resizeGame);
        menu.add(resetButton);
        this.add(menu, BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
        return size;
    }


    private void initialStones(int size) {
        int a = size / 2 - 1;
        int b = size / 2;

        valueY.get(a).get(a).setTaken(Color.WHITE);
        valueY.get(a).get(b).setTaken(Color.BLACK);
        valueY.get(b).get(b).setTaken(Color.WHITE);
        valueY.get(b).get(a).setTaken(Color.BLACK);
    }


    private void createBoardSize(int number, int size, Board board, ArrayList<Tile> valueX ,ArrayList<ArrayList<Tile>> valueY) {
        board.setLayout(new GridLayout(number, number, 1, 1));
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                Tile tile = new Tile(new Color(((i + j) % 2) * 25, ((i + j) % 2 + 1) * 70, 0), size, j, i, valueY);
                board.add(tile);
                valueX.add(tile);
            }
            ArrayList<Tile> shallowCopy = new ArrayList<Tile>(valueX);
            valueY.add(shallowCopy);
            valueX.clear();
        }
    }

    private void addImageIcon() {
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

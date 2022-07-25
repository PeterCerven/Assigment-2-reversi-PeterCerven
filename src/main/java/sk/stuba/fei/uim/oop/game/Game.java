package sk.stuba.fei.uim.oop.game;

import lombok.Getter;
import sk.stuba.fei.uim.oop.game.board.Board;
import sk.stuba.fei.uim.oop.game.menu.*;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.game.menu.Menu;
import sk.stuba.fei.uim.oop.utility.MyKeyAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


@Getter
public class Game extends JFrame {

    private ArrayList<Tile> valueX;
    private ArrayList<ArrayList<Tile>> valueY;
    private GameLogic gameLogic;
    private ResetButton resetButton;
    private ResizeGameComboBox resizeGameComboBox;
    private MyKeyAdapter myKeyAdapter;
    private StoneCountText blackCount;
    private StoneCountText whiteCount;
    private int currentSize = 6;
    private Board board;
    public Menu menu;
    private MyJLabel labelWinner;
    private MyJLabel labelSize;


    public Game() throws HeadlessException {
        super();
        gameCreate();
    }

    private void gameCreate() {
        valueX = new ArrayList<>();
        valueY = new ArrayList<>();
        createFrame();
        addImageIcon();
        addingListeners();
        createMenu();
        gameLogic = new GameLogic(valueY, blackCount, whiteCount, this);
        createBoard(currentSize);
        gameLogic.createPossiblePlacements(Color.BLACK, Color.WHITE, false);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }


    private void createMenu() {
        menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        labelWinner = new MyJLabel("");
        labelWinner.setFont(new Font("Comic Sans", Font.BOLD, 25));
        labelSize = new MyJLabel("Current size: ");
        labelSize.showSize(currentSize);
        menu.add(resetButton);
        menu.add(labelSize);
        menu.add(resizeGameComboBox);
        createCounters();
        menu.add(blackCount);
        menu.add(whiteCount);
        menu.add(labelWinner);
        this.add(menu, BorderLayout.NORTH);
    }


    private void createFrame() {
        this.setTitle("Reversi");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x1F7F25));
        this.addImageIcon();
    }

    private void createCounters() {
        this.blackCount = new StoneCountText("Black:", 2);
        this.whiteCount = new StoneCountText("White:", 2);
    }

    private void createBoard(int size) {
        this.board = new Board(Color.BLACK, 500, 500);
        createBoardSize(size, board, valueX, valueY);
        initialStones(size);
        this.add(board, BorderLayout.CENTER);
    }

    private void addingListeners() {
        this.resetButton = new ResetButton(this);
        Integer[] sizes = {6, 8, 10, 12};
        this.resizeGameComboBox = new ResizeGameComboBox(sizes, this);
        this.myKeyAdapter = new MyKeyAdapter(this);
        this.addKeyListener(myKeyAdapter);
        this.setFocusable(true);
    }


    private void initialStones(int size) {
        int a = size / 2 - 1;
        int b = size / 2;

        valueY.get(a).get(a).setTaken(Color.WHITE);
        valueY.get(a).get(b).setTaken(Color.BLACK);
        valueY.get(b).get(b).setTaken(Color.WHITE);
        valueY.get(b).get(a).setTaken(Color.BLACK);
    }


    private void createBoardSize(int number, Board board, ArrayList<Tile> valueX, ArrayList<ArrayList<Tile>> valueY) {
        Color green1 = new Color(25, 70, 0);
        Color green2 = new Color(25, 140, 0);
        Color current;
        board.setLayout(new GridLayout(number, number, 1, 1));
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                if ((i + j) % 2 == 0) {
                    current = green1;
                } else
                    current = green2;
                Tile tile = new Tile(current, 500 / number, j, i, valueY, this.gameLogic);
                this.board.add(tile);
                valueX.add(tile);
            }
            ArrayList<Tile> shallowCopy = new ArrayList<>(valueX);
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
            e.printStackTrace();
        }
    }

    public void restartGame(int size) {
        labelWinner.showWinner("");
        this.currentSize = size;
        this.remove(board);
        this.valueY.clear();
        this.valueX.clear();
        createBoard(size);
        this.blackCount.ChangeNumberStone(2, "Black:");
        this.whiteCount.ChangeNumberStone(2, "White:");
        labelSize.showSize(currentSize);
        gameLogic.createPossiblePlacements(Color.BLACK, Color.WHITE, false);
        this.pack();
        this.setVisible(true);
    }

    public void gameClose() {
        this.dispose();
        this.setVisible(false);
        System.exit(0);
    }


}

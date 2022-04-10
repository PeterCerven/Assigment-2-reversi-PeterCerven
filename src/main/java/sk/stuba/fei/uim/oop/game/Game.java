package sk.stuba.fei.uim.oop.game;

import lombok.Getter;
import sk.stuba.fei.uim.oop.game.board.Board;
import sk.stuba.fei.uim.oop.game.board.Stone;
import sk.stuba.fei.uim.oop.game.menu.Menu;
import sk.stuba.fei.uim.oop.game.board.Tile;
import sk.stuba.fei.uim.oop.game.menu.ResetButton;
import sk.stuba.fei.uim.oop.game.menu.ResizeGameComboBox;
import sk.stuba.fei.uim.oop.game.menu.StoneCountText;
import sk.stuba.fei.uim.oop.utility.MyKeyAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


@Getter
public class Game extends JFrame implements ActionListener {

    private ArrayList<Tile> valueX = new ArrayList<>();
    private ArrayList<ArrayList<Tile>> valueY = new ArrayList<>();
    private GameLogic gameLogic;
    private ResetButton resetButton;
    private ResizeGameComboBox resizeGameComboBox;
    private MyKeyAdapter myKeyAdapter;
    private StoneCountText blackCount;
    private StoneCountText whiteCount;
    private int currentSize = 6;
    private Board board;
    private Menu menu;


    public Game() throws HeadlessException {
        super();
        gameCreate();
    }

    private void gameCreate() {
        createFrame();
        addImageIcon();
        addingListeners();
        menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        createCounters();
        gameLogic = new GameLogic(valueY, blackCount, whiteCount);
        menu.add(resizeGameComboBox);
        menu.add(resetButton);
        menu.add(blackCount);
        menu.add(whiteCount);
        this.add(menu, BorderLayout.NORTH);
        createBoard(currentSize);
        gameLogic.createPossiblePlacements(Color.BLACK, Color.WHITE, true, true);
        this.setVisible(true);
        this.pack();

    }
    private void createFrame(){
        this.setTitle("Reversi");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x1F7F25));
        this.addImageIcon();
    }

    public void createCounters(){
        this.blackCount = new StoneCountText("Black is:", 2);
        this.whiteCount = new StoneCountText("White is:", 2);
    }

    public void createBoard(int size){
        this.board = new Board(Color.BLACK, 500, 500);
        createBoardSize(size, board, valueX, valueY);
        initialStones(size);
        this.add(board, BorderLayout.CENTER);
    }

    private void addingListeners(){
        this.resetButton = new ResetButton(this);
        String[] sizes = {"6x6","8x8","10x10","12x12"};
        this.resizeGameComboBox = new ResizeGameComboBox(sizes, "name");
        this.resizeGameComboBox.addActionListener(this);
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


    private void createBoardSize(int number, Board board, ArrayList<Tile> valueX ,ArrayList<ArrayList<Tile>> valueY) {
        board.setLayout(new GridLayout(number, number, 1, 1));
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                Tile tile = new Tile(new Color(((i + j) % 2) * 25, ((i + j) % 2 + 1) * 70, 0), 100, j, i, valueY, this.gameLogic);
                board.add(tile);
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

    public void restartGame(int size){
        this.currentSize = size;
        this.remove(board);
        this.valueY.clear();
        this.valueX.clear();
        createBoard(size);
        this.blackCount.ChangeNumberStone(2, "Black is:");
        this.whiteCount.ChangeNumberStone(2, "White is:");
        gameLogic.createPossiblePlacements(Color.BLACK, Color.WHITE, true, true);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.resizeGameComboBox){
            switch(Objects.requireNonNull(resizeGameComboBox.getSelectedItem()).toString()){
                case "6x6":
                    restartGame(6);
                    break;
                case "8x8":
                    restartGame(8);
                    break;
                case "10x10":
                    restartGame(10);
                    break;
                case "12x12":
                    restartGame(12);
                    break;
            }
            resizeGameComboBox.setFocusable(false);
        }
    }

}

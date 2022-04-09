package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.game.board.Board;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;



public class Game extends JFrame implements ActionListener, KeyListener {

    private ArrayList<Tile> valueX = new ArrayList<>();
    private ArrayList<ArrayList<Tile>> valueY = new ArrayList<>();
    private GameLogic gameLogic;
    private ResetButton resetButton;
    private ResizeGameComboBox resizeGameComboBox;
//    private MyKeyAdapter myKeyAdapter;
    private StoneCountText blackCount;
    private StoneCountText whiteCount;
    private int size = 6;


    public Game() throws HeadlessException {
        super();
        gameCreate(this.size);
    }

    private void startGame(Color me, Color enemy) {
        gameLogic.createPossiblePlacements(me, enemy, true, true);
    }

    private void gameCreate(int size) {


        createFrame();
        addImageIcon();
        addingListeners();
        createObjects(size);
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

    public void createCounters(){
        blackCount = new StoneCountText("Black is:", 2);
        whiteCount = new StoneCountText("White is:", 2);
    }

    public void createObjects(int size){
        Board board = new Board(Color.BLACK, 500, 500);
        Menu menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        createCounters();
        gameLogic = new GameLogic(valueY, blackCount, whiteCount);
        createBoardSize(size, 50, board, valueX, valueY);
        initialStones(size);
        menu.add(resizeGameComboBox);
        menu.add(resetButton);
        menu.add(blackCount);
        menu.add(whiteCount);
        this.add(menu, BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
    }

    private void addingListeners(){
        this.resetButton = new ResetButton();
        String[] sizes = {"6x6","8x8","10x10","12x12"};
        this.resizeGameComboBox = new ResizeGameComboBox(sizes, "name");
        resizeGameComboBox.addActionListener(this);
//        this.myKeyAdapter = new MyKeyAdapter();
//        this.addKeyListener(myKeyAdapter);
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
                Tile tile = new Tile(new Color(((i + j) % 2) * 25, ((i + j) % 2 + 1) * 70, 0), size, j, i, valueY, this.gameLogic);
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

    public void restartGame(int size){

        gameCreate(size);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.resizeGameComboBox){
            switch(resizeGameComboBox.getSelectedItem().toString()){
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

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'r'){
            restartGame(this.size);
            System.out.println("lol");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

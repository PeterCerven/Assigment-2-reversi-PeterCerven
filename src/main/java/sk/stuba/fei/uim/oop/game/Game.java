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

    public Game() throws HeadlessException {
        super();
        gameCreate();
    }

    private void startGame(Color me, Color enemy) {
        createPossiblePlacements(me, enemy);
    }

    private void gameCreate() {
        createFrame();
        addImageIcon();
        createObjects();
        initialStones(6);
        startGame(Color.WHITE, Color.BLACK);
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

    public void createObjects(){
        Board board = new Board(Color.BLACK, 500, 500);
        Menu menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        ResetButton resetButton = new ResetButton();
        ResizeGame resizeGame = new ResizeGame(Adjustable.HORIZONTAL, 6, 12, 6);
        createBoardSize(6, 20, board, valueX, valueY);
        menu.add(resizeGame);
        menu.add(resetButton);
        this.add(menu, BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
    }


    public void createPossiblePlacements(Color myColor, Color enemyColor) {
        ArrayList<Point> neighbours = new ArrayList<Point>();
        for (int columns = 0; columns < valueY.size(); columns++) {
            for (int rows = 0; rows < valueY.get(columns).size(); rows++) {
                if (valueY.get(columns).get(rows).getCurrentColor().equals(enemyColor)) {
                    neighbours = findNeighbours(columns, rows, myColor, enemyColor);
                    if (!neighbours.isEmpty()) {
                        for (Point points : neighbours) {
                            valueY.get(points.y).get(points.x).setCandidate(myColor , valueY);
                        }
                    }
                }
            }
        }
    }


    public ArrayList<Point> findNeighbours(int y, int x, Color myColor, Color enemyColor) {
        ArrayList<Point> grey = new ArrayList<>();
        int a;
        int b;
        for (int rows = -1; rows <= 1; rows++) {
            logger:
            for (int columns = -1; columns <= 1; columns++) {
                if (rows == 0 && columns == 0) {
                    continue;
                }
                if (!isOnMap(valueY.size(), rows + x, columns + y)) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows +x).isCanBeTaken()) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows +x).getCurrentColor().equals(myColor) && !(valueY.get(y - columns).get(x - rows).getCurrentColor().equals(enemyColor))) {
                    grey.add(new Point(x - rows, y - columns));
                    continue;
                }
                if (valueY.get(columns + y).get(rows +x).getCurrentColor().equals(enemyColor)) {
                    a = rows;
                    b = columns;
                    while (valueY.get(b + y).get(a + x).getCurrentColor().equals(enemyColor)){
                        a += rows ;
                        b += columns;
                        if (!isOnMap(valueY.size(),b + y,a + x)) {
                            continue logger;
                        }
                    }
                    if (!isOnMap(valueY.size(),b + y,a + x)) {
                        break;
                    }
                    if (valueY.get(b + y).get(a + x).getCurrentColor().equals(myColor)){
                        a -= rows;
                        b -= columns;
                        while(isOnMap(valueY.size(), a + x, b + y)){
                            a -= rows;
                            b -= columns;
                            if(!isOnMap(valueY.size(), a + x, b + y)){
                                break;
                            }
                            if (!(valueY.get(b + y).get(a + x).isTaken() || valueY.get(b + y).get(a + x).isCanBeTaken())){
                                grey.add(new Point(a + x, b + y));
                            } else {
                                break;
                            }
                        }
                    } if (!(valueY.get(b + y).get(a + x).isCanBeTaken() && valueY.get(b + y).get(a + x).isTaken())) {
                        int temp_Y = b + y;
                        int temp_X = a + x;
                        while (isOnMap(valueY.size(), a + x, b + y)){
                            if (valueY.get(b + y).get(a + x).getCurrentColor().equals(myColor)){
                                grey.add(new Point(temp_X, temp_Y));
                                break;
                            }
                            a -= rows;
                            b -= columns;
                        }
                    }
//
                }
            }
        }
        return grey;
    }

    public boolean isOnMap(int size, int x, int y){
        if (x >= (size) || x < 0 || y >= size || y < 0){
            return false;
        }
        return true;
    }



    private void initialStones(int size) {
        int a = size / 2 - 1;
        int b = size / 2;

        valueY.get(a).get(a).setTaken(Color.BLACK);
        valueY.get(a).get(b).setTaken(Color.WHITE);
        valueY.get(b).get(b).setTaken(Color.BLACK);
        valueY.get(b).get(a).setTaken(Color.WHITE);
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

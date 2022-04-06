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

    public ArrayList<Tile> valueX = new ArrayList<>();
    public ArrayList<ArrayList<Tile>> valueY = new ArrayList<>();

    public Game() throws HeadlessException {
        super();
        gameCreate();
        //startGame();
    }

    private void startGame() {

    }

    private void gameCreate() {
        this.setTitle("Reversi");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x1F7F25));
        addImageIcon();
        Board board = new Board(Color.black, 500, 500);
        Menu menu = new Menu(Color.LIGHT_GRAY, 500, 100);
        ResetButton resetButton = new ResetButton();
        ResizeGame resizeGame = new ResizeGame(Adjustable.HORIZONTAL, 6, 12, 6);
        createBoardSize(6, 10, board, valueX, valueY);
        initialStones(6);
        createPossiblePlacements(Color.WHITE, Color.BLACK);
        menu.add(resizeGame);
        menu.add(resetButton);


        this.add(menu, BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);
        System.out.println(valueY.get(0).size());
        System.out.println(6 / 2 - 1);

    }

    private void addStone(int x, int y, Color color) {
        valueY.get(x).get(y).setBackground(color);
    }

    private void createPossiblePlacements(Color myColor, Color enemyColor) {
        ArrayList<Point> neighbours = new ArrayList<Point>();
        for (int i = 0; i < valueY.size(); i++) {
            for (int j = 0; j < valueY.get(i).size(); j++) {
                if (valueY.get(i).get(j).getOwnerColor().equals(enemyColor)) {
                    neighbours = findNeighbours(i, j, myColor, enemyColor);
                    if (!neighbours.isEmpty()) {
                        for (Point points : neighbours) {
                            valueY.get(points.y).get(points.x).setCanditate();
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Point> findNeighbours(int y, int x, Color myColor, Color enemyColor) {
        ArrayList<Point> grey = new ArrayList<>();
        int a;
        int b;
        for (int rows = -1; rows <= 1; rows++) {
            for (int columns = -1; columns <= 1; columns++) {
                if (rows == columns) {
                    continue;
                }
                if (valueY.size() <= (columns + y) || valueY.get(y).size() <= (rows + x)) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows + x).isCanBeTaken()) {
                    continue;
                }
                if (valueY.get(columns + y).get(rows + x).getOwnerColor().equals(myColor)) {
                    grey.add(new Point(x - rows, y - columns));
                    continue;
                }
                if (valueY.get(columns + y).get(rows + x).getOwnerColor().equals(enemyColor)) {
                    a = rows;
                    b = columns;
                    while (valueY.get(b + y).get(a + x).getOwnerColor().equals(enemyColor)){
                        a++;
                        b++;
                    }
                    if (valueY.get(b + y).get(a + x).getOwnerColor().equals(myColor)){
                        grey.add(new Point(x - rows, y - columns));
                    }
                }
            }
        }
        return grey;
    }



    private void initialStones(int size) {
        int a = size / 2 - 1;
        int b = size / 2;

        valueY.get(a).get(a).setTaken(Color.BLACK);
        valueY.get(a).get(b).setTaken(Color.WHITE);
        valueY.get(b).get(b).setTaken(Color.BLACK);
        valueY.get(b).get(a).setTaken(Color.WHITE);
    }


    private void createBoardSize(int number, int size, Board board, ArrayList<Tile> valueX, ArrayList<ArrayList<Tile>> valueY) {
        board.setLayout(new GridLayout(number, number, 1, 1));
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                Tile tile = new Tile(new Color(((i + j) % 2) * 25, ((i + j) % 2 + 1) * 70, 0), 0, 0, size);
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

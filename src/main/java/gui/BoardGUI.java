package main.java.gui;

import javax.swing.JPanel;
import java.awt.*;

import main.java.model.Bloc;
import main.java.model.Board;

public class BoardGUI extends JPanel {

    protected final int marginPoint = 10;
    protected final int marginLength = 20;

    protected Board board;
    protected BlocGUI[][] grid;

    public BoardGUI(int x, int y){
        this.board = new Board(x, y);
        this.grid = new BlocGUI[board.getLenX()][board.getLenY()];

        setLayout(new GridLayout(board.getLenY(), board.getLenX(),1,1));

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BlocGUI();
                this.add(grid[i][j]);
            }
        }

        replace(5, 0, new BlocGUI(Bloc.RED));

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        /*
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(marginPoint, marginPoint, getWidth()-marginLength, getHeight()-marginLength);
        */
    }

    public void replace(int x, int y, BlocGUI bloc){
        this.removeAll();
        for (int j = 0; j < board.getLenY(); j++) {
            for (int i = 0; i < board.getLenX(); i++) {
                if(i == x && j == y){
                    grid[x][y] = bloc;
                }
                else{
                    grid[i][j] = new BlocGUI();
                }
                this.add(grid[i][j]);
            }
        }
    }
}

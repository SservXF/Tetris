package main.java.gui;

import javax.swing.JPanel;
import java.awt.*;

import main.java.model.Board;

public class BoardGUI extends JPanel {

    protected final int marginPoint = 10;
    protected final int marginLength = 20;

    protected Board board;

    public BoardGUI(int x, int y){
        this.board = new Board(x, y);

        setLayout(new GridLayout(board.getLenY(), board.getLenX()));
        System.out.println(board.getLenX());
        System.out.println(board.getLenY());

        JPanel test = new JPanel();
        test.setBackground(Color.CYAN);
        JPanel t = new JPanel();
        t.setBackground(Color.RED);

        this.add(test);
        this.add(t);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(marginPoint, marginPoint, getWidth()-marginLength, getHeight()-marginLength);
    }
}

package main.java.model;

import java.awt.*;

import main.java.gui.BoardGUI;

public class Bloc {

    public enum Couleur {CYAN, BLUE, ORANGE, YELLOW, GREEN, PINK, RED}

    public Couleur couleur;

    public int x;
    public int y;

    public static final int SIZE = 25;

    public Bloc(Couleur c){
        this.couleur = c;
    }

    public void newPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(ColorSwitcher.toColor(couleur));
        g2d.fillRect(BoardGUI.marginPoint + this.x*SIZE, 2*BoardGUI.marginLength + this.y*SIZE, SIZE, SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(BoardGUI.marginPoint + this.x*SIZE, 2*BoardGUI.marginLength + this.y*SIZE, SIZE, SIZE);
    }
}
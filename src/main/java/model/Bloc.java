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

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(ColorSwitcher.toColor(couleur));
        g2d.fillRect(BoardGUI.marginPoint + this.x*SIZE, BoardGUI.marginPoint + this.y*SIZE, SIZE, SIZE);
    }
}
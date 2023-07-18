package main.java.model;

import java.awt.*;

public class Bloc {

    public enum Couleur {CYAN, BLUE, ORANGE, YELLOW, GREEN, PINK, RED}

    public Couleur couleur;

    public int x;
    public int y;

    public static int SIZE = 25;

    public Bloc(Couleur c){
        this.couleur = c;
    }

    public void newPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Méthode pour dessiner un cube
     * @param g le graphics
     * @param dX Le decalage en X
     * @param dY Le decalage en Y
     */
    public void draw(Graphics g, int dX, int dY) {

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(ColorSwitcher.toColor(couleur));
        g2d.fillRect(dX + this.x*SIZE, dY + this.y*SIZE, SIZE, SIZE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(dX + this.x*SIZE, dY + this.y*SIZE, SIZE, SIZE);
    }
}
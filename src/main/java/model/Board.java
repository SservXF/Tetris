package main.java.model;

public class Board {

    /**
     * Pour avoir une taille minimal de jeu
     */
    protected static int minX = 10;
    protected static int minY = 20;

    /**
     * Correspondent à la taille de l'objet "Board"
     */
    protected int lenX;
    protected int lenY;

    protected Bloc[][] gridBlocks;

    public int getLenX(){return lenX;}
    public int getLenY(){return lenY;}

    public Board(int x, int y){
        this.lenX = (x < minX) ? minX : x;
        this.lenY = (y < minY) ? minY : y;

        gridBlocks = new Bloc[this.lenX][this.lenY];
    }
}
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

    protected Bloc[][] gridBlocs;

    public int getLenX(){return lenX;}
    public int getLenY(){return lenY;}
    public Bloc[][] getGridBlocs(){return gridBlocs;}

    public void setGridBlocs(int x, int y, Bloc bloc){
        gridBlocs[x][y] = bloc;
    }

    public Board(int x, int y){
        this.lenX = (x < minX) ? minX : x;
        this.lenY = (y < minY) ? minY : y;

        gridBlocs = new Bloc[this.lenX][this.lenY];
    }
}
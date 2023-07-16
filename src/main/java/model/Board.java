package main.java.model;

import java.util.ArrayList;


public class Board {

    /**
     * Pour avoir une taille minimal de jeu
     */
    protected static final int minX = 10;
    protected static final int minY = 20;

    protected int SCORE = 0;

    /**
     * Correspondent à la taille de l'objet "Board"
     */
    protected int lenX;
    protected int lenY;

    /**
     * La grille contenant les blocs qui représente le plateau du Tetris
     */
    protected Bloc[][] gridBlocs;

    /**
     * Liste qui contient les pièces à dessines sur le plateau
     */
    protected ArrayList<Bloc> blocsToDraw= new ArrayList<Bloc>();

    protected Piece curPiece;

    public int getLenX(){return lenX;}
    public int getLenY(){return lenY;}
    public Bloc[][] getGridBlocs(){return gridBlocs;}
    public ArrayList<Bloc> getBlocsToDraw(){return blocsToDraw;}
    public Piece getCurPiece(){return curPiece;}

    public void setGridBlocs(int x, int y, Bloc bloc){
        gridBlocs[x][y] = bloc;
    }


    public Board(int x, int y){
        this.lenX = (x < minX) ? minX : x;
        this.lenY = (y < minY) ? minY : y;

        gridBlocs = new Bloc[this.lenX][this.lenY];

        newCurPiece();
    }

    public void newCurPiece(){
        curPiece = new Piece(1);
        curPiece.newPos(lenX/2 - curPiece.sizeX(), 0);
    }

    public void rotateCurPiece(){
        if(curPiece.sizeY() + curPiece.posX < lenX && curPiece.sizeX() + curPiece.posY < lenY){
            curPiece.rotateCW();
        }
    }

    public void pieceDropped(){
        placePieceForce(curPiece.posX, curPiece.posY, curPiece);
        removeFullLines();
        newCurPiece();
    }

    public void removeFullLines(){

        ArrayList<Integer> lines = new ArrayList<Integer>();
        boolean isFull = true;

        for (int j = 0; j < lenY; j++) {
            for (int i = 0; i < lenX; i++) {
                System.out.print(gridBlocs[i][j] + " | ");
            }   
            System.out.println();
        }
        
        for (int j = lenY-1; j > 0; j--) {
            for (int i = 0; i < lenX; i++) {
                if(gridBlocs[i][j] == null){
                    isFull = false;
                    break;
                }
            }
            if(isFull){
                lines.add(j);
            }
            isFull = true;
        }

        SCORE+=lines.size();

        for (Integer line : lines) {
            for (int i = 0; i < lenX; i++) {
                blocsToDraw.remove(gridBlocs[i][line]);
                gridBlocs[i][line] = null;
            }
        }
    }

    public void oneLineDown(){
        if(!tryMove(curPiece.posX, curPiece.posY+1)){
            pieceDropped();
        }
    }


    /**
     * Méthode pour remettre à zéro le plateau, 2 étapes :
     * - On retire tous les éléments de la liste des pièces à dessines
     * - On vide le tableau des blocs
     */
    public void clearBoard(){
        blocsToDraw.clear();
        gridBlocs = new Bloc[lenX][lenY];
    }

    /**
     * Méthode pour vérifier si on peut poser un bloc à un endroit précis avec des contraintes
     * - S'il y a deja un bloc, on ne peut pas poser
     * @param x Position en X
     * @param y Position en Y
     * @return True si possible, false sinon
     */
    public boolean canPlaceBloc(int x, int y){
        return gridBlocs[x][y] == null;
    }

    /**
     * Méthode pour forcer un bloc à se placer
     *  /!\ Il faut toujours passer par cette méthode pour placer un bloc
     * @param x Position en X
     * @param y Position en Y
     * @param bloc Le bloc à poser
     * @return Toujours true
     */
    public boolean placeBlocForce(int x, int y, Bloc bloc){
        if(bloc != null){
            gridBlocs[x][y] = bloc;
            blocsToDraw.add(bloc);
        }
        return true;
    }

    /**
     * Méthode pour placer un bloc avec des contraintes :
     * - On ne place pas un bloc s'il y a deja un bloc
     * @param x Position en X
     * @param y Position en Y
     * @param bloc Le bloc à poser
     * @return True si possible, false sinon
     */
    public boolean placeBlocConstraint(int x, int y, Bloc bloc){
        if(canPlaceBloc(x, y)){return placeBlocForce(x, y, bloc);}
        return false;
    }

    /**
     * Méthode pour vérifier si on peut placer une piece à un endroit précis selon des contraintes :
     * - S'il y a des blocs déja places qui genent, on renvoie false
     * @param x Position en X
     * @param y Position en Y
     * @param piece La piece a placer
     * @return True si possible, false sinon
     */
    public boolean canPlacePiece(int x, int y, Piece piece){
        if(x<0 || x+piece.sizeX() >= lenX || y<0 || y+piece.sizeY() >= lenY){return false;}
        for (int j = 0; j < piece.sizeY(); j++) {
            for (int i = 0; i < piece.sizeX(); i++) {
                if(gridBlocs[x+i][y+j] != null && piece.getForme()[i][j] != null){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean placePieceForce(int x, int y, Piece piece){
        for (int j = 0; j < piece.sizeY(); j++) {
            for (int i = 0; i < piece.sizeX(); i++) {
                placeBlocForce(x+i, y+j, piece.forme[i][j]);
            }
        }
        return true;
    }

    public boolean placePieceConstraint(int x, int y, Piece piece){
        if(canPlacePiece(x, y, piece)){return placePieceForce(x, y, piece);}
        return false;
    }

    public boolean tryMove(int newX, int newY){
        if(canPlacePiece(newX, newY, curPiece)){
            curPiece.newPos(newX, newY);
            return true;
        }
        return false;
    }
}
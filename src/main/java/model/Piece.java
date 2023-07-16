package main.java.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import main.java.model.Bloc.Couleur;
import main.java.model.Piece.AllPieces.TypeOfPieces;
import main.java.gui.BoardGUI;

public class Piece {

    public static class SizeOfPiece {

        private int sizeX;
        private int sizeY;

        public SizeOfPiece(int x, int y){
            sizeX = x;
            sizeY = y;
        }

        public int getX(){return sizeX;}
        public int getY(){return sizeY;}
    }

    public static class AllPieces {
        
        protected static enum TypeOfPieces{O,I,S,Z,L,J,T};
        public static final int numberOfPieces = TypeOfPieces.values().length;
    
        public static Bloc[][] pieceO(){
            Bloc[][] pieceO = new Bloc[2][2];
            pieceO[0][0] = new Bloc(Couleur.YELLOW);
            pieceO[0][1] = new Bloc(Couleur.YELLOW);
            pieceO[1][0] = new Bloc(Couleur.YELLOW);
            pieceO[1][1] = new Bloc(Couleur.YELLOW);
            return pieceO;
        }
    
        public static Bloc[][] pieceI(){
            Bloc[][] pieceI = new Bloc[1][4];
            pieceI[0][0] = new Bloc(Couleur.CYAN);
            pieceI[0][1] = new Bloc(Couleur.CYAN);
            pieceI[0][2] = new Bloc(Couleur.CYAN);
            pieceI[0][3] = new Bloc(Couleur.CYAN);
            return pieceI;
        }
    
        public static Bloc[][] pieceS(){
            Bloc[][] pieceS = new Bloc[3][2];
            pieceS[0][1] = new Bloc(Couleur.RED);
            pieceS[1][1] = new Bloc(Couleur.RED);
            pieceS[1][0] = new Bloc(Couleur.RED);
            pieceS[2][0] = new Bloc(Couleur.RED);
            return pieceS;
        }
    
        public static Bloc[][] pieceZ(){
            Bloc[][] pieceZ = new Bloc[3][2];
            pieceZ[0][0] = new Bloc(Couleur.GREEN);
            pieceZ[1][0] = new Bloc(Couleur.GREEN);
            pieceZ[1][1] = new Bloc(Couleur.GREEN);
            pieceZ[2][1] = new Bloc(Couleur.GREEN);
            return pieceZ;
        }
    
        public static Bloc[][] pieceL(){
            Bloc[][] pieceL = new Bloc[2][3];
            pieceL[0][0] = new Bloc(Couleur.ORANGE);
            pieceL[0][1] = new Bloc(Couleur.ORANGE);
            pieceL[0][2] = new Bloc(Couleur.ORANGE);
            pieceL[1][2] = new Bloc(Couleur.ORANGE);
            return pieceL;
        }
    
        public static Bloc[][] pieceJ(){
            Bloc[][] pieceJ = new Bloc[2][3];
            pieceJ[1][0] = new Bloc(Couleur.BLUE);
            pieceJ[1][1] = new Bloc(Couleur.BLUE);
            pieceJ[1][2] = new Bloc(Couleur.BLUE);
            pieceJ[0][2] = new Bloc(Couleur.BLUE);
            return pieceJ;
        }
    
        public static Bloc[][] pieceT(){
            Bloc[][] pieceT = new Bloc[3][2];
            pieceT[0][0] = new Bloc(Couleur.PINK);
            pieceT[1][0] = new Bloc(Couleur.PINK);
            pieceT[2][0] = new Bloc(Couleur.PINK);
            pieceT[1][1] = new Bloc(Couleur.PINK);
            return pieceT;
        }
    
        public static Bloc[][] giveByID(int id){
            id = id % numberOfPieces;
            switch (id) {
                case 0:return pieceO();
                case 1:return pieceI();
                case 2:return pieceS();
                case 3:return pieceZ();
                case 4:return pieceL();
                case 5:return pieceJ();
                default:return pieceT();
            }
        }

        public static TypeOfPieces getType(int id){
            return TypeOfPieces.values()[id%numberOfPieces];
        }

        public static SizeOfPiece getSize(int id){
            id = id % numberOfPieces;
            switch (id) {
                case 0:return new SizeOfPiece(2, 2);
                case 1:return new SizeOfPiece(1, 4);
                case 2:return new SizeOfPiece(3, 2);
                case 3:return new SizeOfPiece(3, 2);
                case 4:return new SizeOfPiece(2, 3);
                case 5:return new SizeOfPiece(2, 3);
                default:return new SizeOfPiece(3, 2);
            }
        }
        
    }

    /**
     * Attribut pour connaitre le type de la piece
     */
    protected TypeOfPieces type;

    /**
     * Matrix contenant la forme de la piece
     */
    protected Bloc[][] forme;
    /**
     * Position sur le plateau en X
     */
    public int posX;
    /**
     * Position sur le plateau en Y
     */
    public int posY;

    /**
     * Pour connaitre la taille de la pièce
     */
    protected SizeOfPiece size;

    public Bloc[][] getForme(){return forme;}
    public int sizeX(){return size.getX();}
    public int sizeY(){return size.getY();}

    public Piece(int id){
        forme = AllPieces.giveByID(id);
        type = AllPieces.getType(id);
        size = AllPieces.getSize(id);
    }

    public Piece(){
        this(new Random().nextInt(AllPieces.numberOfPieces));
    }

    public void newPos(int newX, int newY){
        this.posX = newX;
        this.posY = newY;
        for (int j = 0; j < sizeY(); j++) {
            for (int i = 0; i < sizeX(); i++) {
                if(forme[i][j] != null){
                    forme[i][j].x = newX+i;
                    forme[i][j].y = newY+j;
                }
            }
        }
    }

    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

        for (int j = 0; j < sizeY(); j++) {
            for (int i = 0; i < sizeX(); i++) {
                if(forme[i][j] != null){
                    g2d.setColor(ColorSwitcher.toColor(forme[i][j].couleur));
                    forme[i][j].draw(g2d);
                }
            }
        }
    }

    public void print(){
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                if(forme[x][y] != null){System.out.print("#");}
                else{System.out.print(" ");}
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Piece p = new Piece(1);
        p.print();
    }
}
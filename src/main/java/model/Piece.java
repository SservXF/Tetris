package main.java.model;

public class Piece {

    protected Bloc[][] piece;

    public Piece(AllPieces.TypesOfPieces id){
        piece = AllPieces.giveByID(id);
    }

    public Piece(int id){
        piece = AllPieces.giveByID(id);
    }

    public void print(){
        for (int y = 0; y < AllPieces.size; y++) {
            for (int x = 0; x < AllPieces.size; x++) {
                if(piece[x][y] != null){System.out.print("#");}
                else{System.out.print(" ");}
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Piece p = new Piece(0);
        p.print();
    }
}
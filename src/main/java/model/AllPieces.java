package main.java.model;

public class AllPieces {
    
    protected static final int size = 4;
    protected static final int numberOfPieces = 7;
    protected static enum TypesOfPieces{O,I,S,Z,L,J,T};

    public static Bloc[][] pieceO(){
        Bloc[][] pieceO = new Bloc[size][size];
        pieceO[0][0] = Bloc.YELLOW;
        pieceO[0][1] = Bloc.YELLOW;
        pieceO[1][0] = Bloc.YELLOW;
        pieceO[1][1] = Bloc.YELLOW;
        return pieceO;
    }

    public static Bloc[][] pieceI(){
        Bloc[][] pieceI = new Bloc[size][size];
        pieceI[0][0] = Bloc.CYAN;
        pieceI[0][1] = Bloc.CYAN;
        pieceI[0][2] = Bloc.CYAN;
        pieceI[0][3] = Bloc.CYAN;
        return pieceI;
    }

    public static Bloc[][] pieceS(){
        Bloc[][] pieceS = new Bloc[size][size];
        pieceS[0][1] = Bloc.RED;
        pieceS[1][1] = Bloc.RED;
        pieceS[1][0] = Bloc.RED;
        pieceS[2][0] = Bloc.RED;
        return pieceS;
    }

    public static Bloc[][] pieceZ(){
        Bloc[][] pieceZ = new Bloc[size][size];
        pieceZ[0][0] = Bloc.GREEN;
        pieceZ[1][0] = Bloc.GREEN;
        pieceZ[1][1] = Bloc.GREEN;
        pieceZ[2][1] = Bloc.GREEN;
        return pieceZ;
    }

    public static Bloc[][] pieceL(){
        Bloc[][] pieceL = new Bloc[size][size];
        pieceL[0][0] = Bloc.ORANGE;
        pieceL[0][1] = Bloc.ORANGE;
        pieceL[0][2] = Bloc.ORANGE;
        pieceL[1][2] = Bloc.ORANGE;
        return pieceL;
    }

    public static Bloc[][] pieceJ(){
        Bloc[][] pieceJ = new Bloc[size][size];
        pieceJ[1][0] = Bloc.BLUE;
        pieceJ[1][1] = Bloc.BLUE;
        pieceJ[1][2] = Bloc.BLUE;
        pieceJ[0][2] = Bloc.BLUE;
        return pieceJ;
    }

    public static Bloc[][] pieceT(){
        Bloc[][] pieceT = new Bloc[size][size];
        pieceT[0][0] = Bloc.PINK;
        pieceT[1][0] = Bloc.PINK;
        pieceT[2][0] = Bloc.PINK;
        pieceT[1][1] = Bloc.PINK;
        return pieceT;
    }

    public static Bloc[][] giveByID(TypesOfPieces id){
        switch (id) {
            case O:return pieceO();
            case I:return pieceI();
            case S:return pieceS();
            case Z:return pieceZ();
            case L:return pieceL();
            case J:return pieceJ();
            default:return pieceT();
        }
    }

    public static Bloc[][] giveByID(int id){
        id = id % TypesOfPieces.values().length;
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
    
}
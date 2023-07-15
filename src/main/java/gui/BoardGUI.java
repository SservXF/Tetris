package main.java.gui;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import main.java.model.AllPieces;
import main.java.model.Bloc;
import main.java.model.Board;
import main.java.model.Piece;

public class BoardGUI extends JPanel implements ActionListener {

    protected static int barreDesTaches = 100;
    protected static Dimension screen = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-barreDesTaches));

    protected Board board;
    protected BlocGUI[][] grid;

    protected Piece userPiece;
    protected static int DELAY = 10;
    protected Timer timer;
    protected boolean isFallingFinished = false;
    protected boolean isStarted = false;
    protected boolean isPaused = false;
    protected int score = 0;

    public BoardGUI(int x, int y){
        this.board = new Board(x, y);
        this.grid = new BlocGUI[board.getLenX()][board.getLenY()];

        setLayout(new GridLayout(board.getLenY(), board.getLenX(),1,1));
        double ratio = board.getLenY()>board.getLenX() ? board.getLenY()/board.getLenX() : board.getLenX()/board.getLenY();
        this.setPreferredSize(new Dimension((int)(screen.getHeight()/ratio), (int)screen.getHeight()));

        clearBoard();

        timer = new Timer(DELAY, this);

        newPiece();
        //replacePiece(0, 0, new Piece(4));
        //replace(7, 7, new BlocGUI(Bloc.RED));

        oneLineDown();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        /*
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(marginPoint, marginPoint, getWidth()-marginLength, getHeight()-marginLength);
        */
    }

    public void clearBoard(){
        this.removeAll();
        for (int j = 0; j < board.getLenY(); j++) {
            for (int i = 0; i < board.getLenX(); i++) {
                grid[i][j] = new BlocGUI(null);
                this.add(grid[i][j]);
            }
        }
        repaint();
    }

    private void start() {
        isStarted = true;
        isFallingFinished = false;
        score = 0;
        clearBoard();
        newPiece();
        timer.start();
    }

    public void replace(int x, int y, BlocGUI bloc){
        this.removeAll();
        for (int j = 0; j < board.getLenY(); j++) {
            for (int i = 0; i < board.getLenX(); i++) {
                if(i == x && j == y){
                    grid[x][y] = bloc;
                }
                this.add(grid[i][j]);
            }
        }
    }

    /**
     * Méthode pour placer une pièce sur le plateau
     * @param x coord en x (coin en haut à gauche)
     * @param y coord en y (coin en haut à gauche)
     * @param piece La pièce à placée
     */
    public void replacePiece(int x, int y, Piece piece){
        int X=0;
        int Y=0;
        this.removeAll();
        for (int j = 0; j < board.getLenY(); j++) {
            for (int i = 0; i < board.getLenX(); i++) {
                if((i >= x && i < x+AllPieces.size) && (j >= y && j < y+AllPieces.size)){
                    if(piece.getPieceBlocs()[X][Y] != null){
                        grid[i][j] = new BlocGUI(piece.getPieceBlocs()[X][Y]);
                    }
                    X++;
                    if(X == AllPieces.size){
                        X = 0;
                        Y++;
                    }
                }
                this.add(grid[i][j]);
            }
        }
        piece.posX = x;
        piece.posY = y;
    }
    
    /**
     * Méthode pour donner au joueur la pièce à jouer (elle est donnée aléatoirement pour le moment)
     */
    public void newPiece(){
        userPiece = new Piece(new Random().nextInt(AllPieces.numberOfPieces));
        replacePiece(board.getLenX()/2-1, 0, userPiece);
    }

    public void refreshBoard(){
        this.removeAll();
        for (int j = 0; j < board.getLenY(); j++) {
            for (int i = 0; i < board.getLenX(); i++) {
                grid[i][j] = new BlocGUI(board.getGridBlocs()[i][j]);
                this.add(grid[i][j]);
            }
        }
        repaint();
    }

    public boolean tryMove(Piece piece, int newX, int newY){
        for (int y = 0; y < AllPieces.size; y++) {
            for (int x = 0; x < AllPieces.size; x++) {
                if(piece.getPieceBlocs()[x][y] != null && board.getGridBlocs()[newX+x][newY+y] != null){
                    return false;
                }
            }
        }

        for (int y = 0; y < AllPieces.size; y++) {
            for (int x = 0; x < AllPieces.size; x++) {
                if(piece.getPieceBlocs()[x][y] != null){
                    board.setGridBlocs(piece.posX+x, piece.posY+y, null);
                }
            }
        }

        replacePiece(newX, newY, piece);
        //refreshBoard();
        return true;
    }

    public void pieceDropped(){
        removeFullLines();
        newPiece();
    }

    private void oneLineDown(){
        if (!tryMove(userPiece, userPiece.posX, userPiece.posY + 1)){
            pieceDropped();
        }
    }

    public void removeFullLines(){
        // TODO : à faire
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }
}

package main.java.gui;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import main.java.model.Bloc;
import main.java.model.Board;

public class BoardGUI extends JPanel implements ActionListener, KeyListener {

    public static final int marginPoint = 5;
    protected static final int marginLength = 10;

    //protected static int barreDesTaches = 100;
    //protected static Dimension screen = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-barreDesTaches));

    protected Board board;

    protected static int DELAY = 600;
    protected Timer timer;
    protected boolean isFallingFinished = false;
    protected boolean isStarted = false;
    protected boolean isPaused = false;

    public BoardGUI(int x, int y){
        setFocusable(true);
        addKeyListener(this);
        
        this.board = new Board(x, y);

        setLayout(new GridLayout(board.getLenY(), board.getLenX(),1,1));
        this.setPreferredSize(new Dimension(board.getLenX()*Bloc.SIZE, board.getLenY()*Bloc.SIZE));

        timer = new Timer(DELAY, this);

        start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(marginPoint, marginPoint, getWidth()-marginLength, getHeight()-marginLength);

        g2d.setStroke(new BasicStroke());
        for (Bloc b : board.getBlocsToDraw()) {
            b.draw(g2d);
        }

        board.getCurPiece().draw(g2d);

    }

    private void start() {
        isStarted = true;
        isFallingFinished = false;
        board.clearBoard();
        board.newCurPiece();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            board.newCurPiece();
        } else {
            board.oneLineDown();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'q':
                System.out.println("ok");
                board.tryMove(board.getCurPiece().posX - 1, board.getCurPiece().posY);
                break;
            case 'd':
                board.tryMove(board.getCurPiece().posX + 1, board.getCurPiece().posY);
                break;
            case 's':
                //RotateMatrix.rightRotate(board.getCurPiece().getForme(), AllPieces.size);
                board.tryMove(board.getCurPiece().posX, board.getCurPiece().posY);
                break;
            case 'z':
                board.tryMove(board.getCurPiece().posX, board.getCurPiece().posY);
                break;
            case KeyEvent.VK_SPACE:
                //dropDown();
                break;
            case 'a':
                board.oneLineDown();
                break;
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {}
}

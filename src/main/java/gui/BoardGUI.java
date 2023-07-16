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
    public static final int marginLength = 10;

    //protected static int barreDesTaches = 100;
    //protected static Dimension screen = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-barreDesTaches));

    protected Board board;

    protected static int NORMAL_DELAY = 600;
    protected static int SPEED_DELAY = 50;
    protected Timer timer;
    protected boolean isFallingFinished = false;
    protected boolean isStarted = false;
    protected boolean isPaused = false;

    public BoardGUI(int x, int y){
        setFocusable(true);
        addKeyListener(this);
        
        this.board = new Board(x, y);

        this.setPreferredSize(new Dimension(board.getLenX()*(Bloc.SIZE-1) - marginPoint, board.getLenY()*Bloc.SIZE));
        this.setBackground(Color.LIGHT_GRAY);

        timer = new Timer(NORMAL_DELAY, this);

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
        isPaused = false;
        isFallingFinished = false;
        board.clearBoard();
        board.newCurPiece();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.oneLineDown();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'q':
                if(!isPaused) board.tryMove(board.getCurPiece().posX - 1, board.getCurPiece().posY);
                break;
            case 'd':
                if(!isPaused) board.tryMove(board.getCurPiece().posX + 1, board.getCurPiece().posY);
                break;
            case 'z':
            case 's':
                if(!isPaused){
                    board.rotateCurPiece();
                    board.tryMove(board.getCurPiece().posX, board.getCurPiece().posY);
                }
                break;

            case 'p':
                if(!isPaused){
                    timer.stop();
                    isPaused = true;
                }
                else{
                    timer.start();
                    isPaused = false;
                }
                break;
            case 'a':
                board.oneLineDown();
                break;
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_SPACE:
            timer.setDelay(50);
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_SPACE:
            timer.setDelay(NORMAL_DELAY);
            break;
        }
    }
}

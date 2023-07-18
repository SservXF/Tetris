package main.java.gui;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import main.java.gui.OptionsGUI.Arrow;
import main.java.model.Bloc;
import main.java.model.Board;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BoardGUI extends JPanel implements ActionListener, KeyListener {

    protected WindowTetris frame;

    //protected static int barreDesTaches = 100;
    //protected static Dimension screen = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-barreDesTaches));

    protected Board board;

    protected static int NORMAL_DELAY = 600;
    protected static int SPEED_DELAY = 50;
    protected Timer timer;
    protected boolean isPaused = false;

    protected Arrow retour = new Arrow(true);

    public BoardGUI(WindowTetris frame, int x, int y){

        this.frame = frame;
        
        setFocusable(true);
        addKeyListener(this);
        
        this.board = new Board(x, y);

        frame.setMinimumSize(new Dimension(board.getLenX()*Bloc.SIZE, board.getLenY()*Bloc.SIZE));

        //this.setPreferredSize(new Dimension(board.getLenX()*(Bloc.SIZE+1), board.getLenY()*(Bloc.SIZE+1)+marginPoint));
        this.setBackground(Color.LIGHT_GRAY);

        timer = new Timer(NORMAL_DELAY, this);

        /*
         * Pour que la taille des blocs se redimensionnent en fonction de la taille de l'écran
         */
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Bloc.SIZE = BoardGUI.this.getHeight() / board.getLenY();
                if(BoardGUI.this.getWidth() < board.getLenX()*Bloc.SIZE){
                    Bloc.SIZE = BoardGUI.this.getWidth() / board.getLenX();
                    retour.setPreferredSize(new Dimension(Bloc.SIZE*2,Bloc.SIZE*2));
                }
                super.componentResized(e);
            }
        });

        start();

        retour.setLayout(null);
        retour.addActionListener(e -> {
            timer.stop();
            frame.setPanel(frame.menuGUI);
        });
        this.add(retour);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        int dX = this.getWidth()/2-(board.getLenX()*Bloc.SIZE)/2;
        int dY = this.getHeight()/2-(board.getLenY()*Bloc.SIZE)/2;
        
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(dX, dY, board.getLenX()*Bloc.SIZE, board.getLenY()*Bloc.SIZE);

        g2d.setStroke(new BasicStroke());
        for (Bloc b : board.getBlocsToDraw()) {
            b.draw(g2d,dX, dY);
        }

        board.getCurPiece().draw(g2d, dX, dY);

    }

    private void start() {
        isPaused = false;
        board.clearBoard();
        board.newCurPiece();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!board.isFinished){
            board.oneLineDown();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(!isPaused && !board.isFinished){
            switch (e.getKeyChar()) {
                case 'q':
                    board.tryMove(board.getCurPiece().posX - 1, board.getCurPiece().posY);
                    break;
                case 'd':
                    board.tryMove(board.getCurPiece().posX + 1, board.getCurPiece().posY);
                    break;
                case 'z':
                case 's':
                    board.rotateCurPiece();
                    board.tryMove(board.getCurPiece().posX, board.getCurPiece().posY);
                    break;
            }
        }
        if(!board.isFinished){
            switch (e.getKeyChar()) {
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

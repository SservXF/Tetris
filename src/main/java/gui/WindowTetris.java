package main.java.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.model.Board;

public class WindowTetris extends JFrame {

    protected MenuGUI menuGUI;
    protected OptionsGUI optionGUI;

    protected static int lenX = Board.minX;
    protected static int lenY = Board.minY;

    protected FontCustom pixelFont = new FontCustom();

    public WindowTetris(){

        // Fonctions permettant l'affichage correcte de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("TETRIS");

        setMinimumSize(new Dimension(600, 300));

        menuGUI = new MenuGUI(this);
        optionGUI = new OptionsGUI(this);
    }

    public void setPanel(JPanel panel){
        this.setContentPane(panel);
        this.invalidate();
        this.validate();
        panel.grabFocus();
    }

    public void start(){
        //setPanel(new BoardGUI(0, 0));
        setPanel(menuGUI);
        this.pack();
        this.setVisible(true);
    }

}

package main.java.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowTetris extends JFrame {

    protected MenuGUI menuGUI;

    protected static int lenX = 0;
    protected static int lenY = 0;

    public WindowTetris(){

        // Fonctions permettant l'affichage correcte de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("TETRIS");

        setMinimumSize(new Dimension(600, 300));

        menuGUI = new MenuGUI(this);
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

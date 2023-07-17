package main.java.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowTetris extends JFrame {

    public WindowTetris(){

        // Fonctions permettant l'affichage correcte de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("TETRIS");
    }

    public void setPanel(JPanel panel){
        this.setContentPane(panel);
        this.invalidate();
        this.validate();
    }

    public void start(){
        setPanel(new BoardGUI(0, 0));
        this.pack();
        this.setVisible(true);
    }

}

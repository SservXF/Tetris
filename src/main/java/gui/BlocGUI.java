package main.java.gui;

import javax.swing.JPanel;

import main.java.model.Bloc;
import main.java.model.ColorSwitcher;

import java.awt.*;
import java.util.Random;

public class BlocGUI extends JPanel {

    public BlocGUI(Bloc b){
        setBackground(ColorSwitcher.toColor(b));
        setOpaque(true);
    }

    public BlocGUI(){
        setOpaque(false);
        /*
        Random r = new Random();
        setBackground(ColorSwitcher.toColor(Bloc.values()[r.nextInt(Bloc.values().length)]));
        */
    }
    
}

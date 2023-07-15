package main.java.gui;

import javax.swing.JPanel;

import main.java.model.Bloc;
import main.java.model.ColorSwitcher;

import java.awt.*;
import java.util.Random;

public class BlocGUI extends JPanel {

    public BlocGUI(Bloc b){
        if(b == null){
            setOpaque(false);
        }
        else{
            setBackground(ColorSwitcher.toColor(b));
            setOpaque(true);
        }
    }
}

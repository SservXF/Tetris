package main.java.gui;

import javax.swing.JPanel;

import main.java.model.Bloc;
import main.java.model.ColorSwitcher;

import java.awt.*;

public class BlocGUI extends JPanel {

    public BlocGUI(Bloc b){
        setBackground(ColorSwitcher.toColor(b));
    }
    
}

package main.java.gui;

import javax.swing.*;
import java.awt.*;

public class ButtonCustom extends JButton {

    public ButtonCustom(String string, float size){

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.setText(string);
        this.setFont(FontCustom.getFont(size));
        this.setBorder(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

        this.setForeground(new Color(255,255,255));

    }

    public void setFontSize(float size){
        this.setFont(FontCustom.getFont(size));
    }
}

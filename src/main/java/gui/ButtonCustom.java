package main.java.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ButtonCustom extends JButton {

    protected static Font pixelFontYS;

    public ButtonCustom(String string, float size){

        if(pixelFontYS == null){
            try {
                pixelFontYS = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\java\\gui\\resources\\PixelFontYS.ttf")).deriveFont(size);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }

        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.setText(string);
        this.setFont(pixelFontYS);
        this.setBorder(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

        this.setForeground(new Color(255,255,255));

    }

    public void setFontSize(float size){
        Font pixelFontV2 = pixelFontYS.deriveFont(size);
        this.setFont(pixelFontV2);
    }
}

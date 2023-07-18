package main.java.gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontCustom {

    private static Font pixelFontYS;

    public FontCustom(){

        if(pixelFontYS == null){
            try {
                pixelFontYS = Font.createFont(Font.TRUETYPE_FONT, new File("src\\main\\java\\gui\\resources\\PixelFontYS.ttf")).deriveFont(10);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Font getFont(){return pixelFontYS;}
    public static Font getFont(float size){return pixelFontYS.deriveFont(size);}
    
}

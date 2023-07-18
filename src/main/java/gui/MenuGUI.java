package main.java.gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class MenuGUI extends JPanel {

    protected static Image background;
    protected static Image scaledBackground;

    public MenuGUI(){

        setLayout(new BorderLayout());

        try {
            background = ImageIO.read(new File("src\\main\\java\\gui\\resources\\backgroundMenu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                scaledBackground = background.getScaledInstance(MenuGUI.this.getWidth(), MenuGUI.this.getHeight(), Image.SCALE_REPLICATE);
                MenuGUI.this.repaint();
                super.componentResized(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d = (Graphics2D) g2d.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        if (scaledBackground == null){
            g2d.drawImage(background, 0, 0, this);
        }
        else {
            g2d.drawImage(scaledBackground, 0, 0, this);
        }

        g2d.dispose();
    }



}

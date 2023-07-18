package main.java.gui;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class MenuGUI extends JPanel {

    protected WindowTetris frame;

    protected static float FONT_SIZE;
    protected static float FONT_RATIO = 20;

    protected static Image background;
    protected static Image scaledBackground;

    protected JLabel buttonsHolder;
    protected ButtonCustom jouer;
    protected ButtonCustom options;
    protected ButtonCustom quitter;

    protected ButtonCustom[] buttonsTab;

    public MenuGUI(WindowTetris frame){

        this.frame = frame;

        setLayout(new BorderLayout());
        updateFontSize();

        try {
            background = ImageIO.read(new File("src\\main\\java\\gui\\resources\\backgroundMenu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                scaledBackground = background.getScaledInstance(MenuGUI.this.getWidth(), MenuGUI.this.getHeight(), Image.SCALE_REPLICATE);
                updateFontSize();
                resizeButtons(MenuGUI.this.getHeight()/FONT_RATIO);
                buttonsHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 100, MenuGUI.this.getHeight()/2  - (int)FONT_SIZE/2));
                MenuGUI.this.repaint();
                super.componentResized(e);
            }
        });

        this.initButtons();
    }

    private void initButtons(){

        buttonsHolder = new JLabel();
        buttonsHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));

        jouer = new ButtonCustom("J O U E R",FONT_SIZE);
        jouer.addActionListener(e -> {
            frame.setPanel(new BoardGUI(frame, WindowTetris.lenX, WindowTetris.lenY));
        });

        options = new ButtonCustom("O P T I O N S",FONT_SIZE);
        options.addActionListener(e -> {
            frame.setPanel(frame.optionGUI);
        });

        quitter = new ButtonCustom("Q U I T T E R",FONT_SIZE);
        quitter.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });

        buttonsTab = new ButtonCustom[3];
        buttonsTab[0] = jouer;
        buttonsTab[1] = options;
        buttonsTab[2] = quitter;

        buttonsHolder.add(jouer);
        buttonsHolder.add(options);
        buttonsHolder.add(quitter);
        this.add(buttonsHolder);
    }

    private void resizeButtons(float size){
        for (ButtonCustom b : buttonsTab) {
            b.setFontSize(size);
        }
    }

    private void updateFontSize(){
        FONT_SIZE = this.getHeight()/FONT_RATIO;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d = (Graphics2D) g2d.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        if (scaledBackground == null){g2d.drawImage(background, 0, 0, this);}
        else {g2d.drawImage(scaledBackground, 0, 0, this);}

        g2d.dispose();
    }
}

package main.java.gui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.java.model.Board;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class OptionsGUI extends JPanel {

    protected WindowTetris frame;

    protected static final int nb_panels = 6;
    protected JPanel[] panels = new JPanel[nb_panels];

    protected static float FONT_SIZE;
    protected static float FONT_RATIO = 20;

    protected static Image background;
    protected static Image scaledBackground;

    protected JLabel buttonsHolder;

    public OptionsGUI(WindowTetris frame){

        this.frame = frame;

        setLayout(new BorderLayout());
        updateFontSize();

        try {
            background = ImageIO.read(new File("src\\main\\java\\gui\\resources\\backgroundOptions1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                scaledBackground = background.getScaledInstance(OptionsGUI.this.getWidth(), OptionsGUI.this.getHeight(), Image.SCALE_REPLICATE);
                updateFontSize();
                resizeButtons(OptionsGUI.this.getHeight()/FONT_RATIO);
                buttonsHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 100, OptionsGUI.this.getHeight()/2  - (int)FONT_SIZE/2));
                OptionsGUI.this.repaint();
                super.componentResized(e);
            }
        });

        this.initButtons();
    }

    private class ChoiceOfOptions extends ButtonCustom {
    
        protected ChoiceOfOptions(String s, float size){
            super(s,size);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            this.setForeground(Color.BLACK);
        }
    }

    public static class Arrow extends JButton {

		protected ImageIcon arrow_left = new ImageIcon("src\\main\\java\\gui\\resources\\arrow_left.png");
		protected ImageIcon arrow_right = new ImageIcon("src\\main\\java\\gui\\resources\\arrow_right.png");

		public Arrow(boolean isLeft){
			super();
			this.setIcon(isLeft ? arrow_left : arrow_right);
			this.setPreferredSize(new Dimension(50, 50));
			this.setBackground(new Color(245,236,206));
			this.setFocusPainted(false);
			this.setOpaque(false);
			this.setBorder(null);
		}
	}

    public class Value extends JLabel {
    
        protected Value(int number, int pos, float size){
            super(String.valueOf(number),pos);
            this.setFont(FontCustom.getFont(size));
            this.setForeground(Color.BLACK);
            this.setBorder(new LineBorder(Color.BLACK));
            this.setBackground(Color.WHITE);
            this.setOpaque(true);
        }

        public void setNumber(int n){
            setText(String.valueOf(n));
            repaint();
        }
    }

    private void initButtons(){

        buttonsHolder = new JLabel();
        buttonsHolder.setLayout(new GridLayout(nb_panels, 1));

		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
			panels[i].setOpaque(false);
		}

        ChoiceOfOptions longueur = new ChoiceOfOptions("L O N G U E U R : ", FONT_SIZE);
        Arrow leftLongueur = new Arrow(true);
        Value valueLongueur = new Value(WindowTetris.lenX, JLabel.CENTER, FONT_SIZE);
        Arrow rightLongueur = new Arrow(false);

        leftLongueur.addActionListener(e -> {
            if(WindowTetris.lenX > Board.minX){
                WindowTetris.lenX--;
                valueLongueur.setNumber(WindowTetris.lenX);
            }
        });

        rightLongueur.addActionListener(e -> {
            if(WindowTetris.lenX < Board.maxX){
                WindowTetris.lenX++;
                valueLongueur.setNumber(WindowTetris.lenX);
            }
        });

        ChoiceOfOptions largeur = new ChoiceOfOptions("L A R G E U R : ", FONT_SIZE);
        Arrow leftLargeur = new Arrow(true);
        Value valueLargeur = new Value(WindowTetris.lenY, JLabel.CENTER, FONT_SIZE);
        Arrow rightLargeur = new Arrow(false);

        leftLargeur.addActionListener(e -> {
            if(WindowTetris.lenY > Board.minY){
                WindowTetris.lenY--;
                valueLargeur.setNumber(WindowTetris.lenY);
            }
        });

        rightLargeur.addActionListener(e -> {
            if(WindowTetris.lenY < Board.maxY){
                WindowTetris.lenY++;
                valueLargeur.setNumber(WindowTetris.lenY);
            }
        });

        ButtonCustom retour = new ButtonCustom("R E T O U R", FONT_SIZE);
        retour.addActionListener(e -> {
            frame.setPanel(frame.menuGUI);
        });
        retour.setForeground(Color.BLACK);

        panels[0].add(longueur);
        panels[0].add(leftLongueur);
        panels[0].add(valueLongueur);
        panels[0].add(rightLongueur);

        panels[1].add(largeur);
        panels[1].add(leftLargeur);
        panels[1].add(valueLargeur);
        panels[1].add(rightLargeur);

        panels[2].add(retour);

        for (JPanel p : panels) {
			buttonsHolder.add(p);
		}

        this.add(buttonsHolder);
    }

    private void resizeButtons(float size){
        for (JPanel p : panels) {
            for (Object b : p.getComponents()) {
                if(b instanceof ButtonCustom){
                    ((ButtonCustom)b).setFontSize(size);
                }
                else if(b instanceof Arrow){
                    ((Arrow)b).setPreferredSize(new Dimension((int)size,(int)size));
                }
                else if(b instanceof Value){
                    ((Value)b).setFont(FontCustom.getFont(size));
                }
            }
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

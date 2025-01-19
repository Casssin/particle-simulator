package ui;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ParticleButton extends JButton {
    private static final int BUTTON_RATIO = 30;
    private static final Dimension SIZE =  new Dimension(MainPanel.SCREEN_HEIGHT / BUTTON_RATIO, MainPanel.SCREEN_HEIGHT / BUTTON_RATIO);
    private int currParticle;
    private Color color;
    private MainPanel mp;
    
    public ParticleButton(int currParticle, Color color, MainPanel mp) {
        super();
        this.mp = mp;
        this.currParticle = currParticle;
        this.color = color;
        this.setBackground(color);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(true);
        this.setContentAreaFilled(false);
        this.setPreferredSize(SIZE);
        this.setMinimumSize(SIZE);
        this.setMaximumSize(SIZE);
        this.addActionListener(new ParticleClickHandler());
        this.addMouseListener(new ParticleMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (mp.currParticle == currParticle) {
            g.setColor(Color.YELLOW); // Selection border color
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        } 
        super.paintComponent(g);
    }

    private class ParticleClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mp.currParticle = currParticle;
        }
    }

    private class ParticleMouseListener extends MouseAdapter {
        public void mouseEntered(MouseEvent evt) {
            mp.setLabel(currParticle);
        }

        public void mouseExited(MouseEvent evt) {
            mp.setLabel(0);
        }
    }
}

package ui;

import javax.swing.*;

import model.Sand;
import model.Screen;
import model.Air;
import model.Particle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements MouseMotionListener {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int PARTICLE_SIZE = 4;
    public static final int ARR_WIDTH = SCREEN_WIDTH / PARTICLE_SIZE;
    public static final int ARR_HEIGHT = SCREEN_HEIGHT / PARTICLE_SIZE;
    public static final long TIME_DELAY = 25;
    private Screen screen;
    private int currParticle;

    public MainPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.gray);
        screen = Screen.getInstance();
        this.addMouseMotionListener(this);
        currParticle = 1;
    }
    
    public void paint(Graphics g) {
        g = (Graphics2D) g;

        for (int i = 0; i < ARR_WIDTH; i++) {
            for (int j = 0; j < ARR_HEIGHT; j++) {
                g.setColor(screen.getValue(i, j).getColor());
                g.fillRect(i * PARTICLE_SIZE, j * PARTICLE_SIZE, PARTICLE_SIZE, PARTICLE_SIZE);
            }
        }
    }

    public void gameLoop() {
        while (true) {
            try {
                Thread.sleep(TIME_DELAY);
            } catch (InterruptedException e) {
                System.out.println("Caught InterruptedException");
                System.exit(0);
            }
            screen.update();
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / PARTICLE_SIZE;
        int y = e.getY() / PARTICLE_SIZE;
        screen.changeValue(x, y, selectedParticle(x, y));
        this.repaint();
    }

    private Particle selectedParticle(int x, int y) {
        if (currParticle == 1) {
            return new Sand(x, y);
        } else {
            return new Air(x, y);
        }
    }
}

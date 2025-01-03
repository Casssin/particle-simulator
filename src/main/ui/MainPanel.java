package ui;

import javax.swing.*;

import model.Sand;
import model.Screen;
import model.Water;
import model.Air;
import model.Particle;
import model.Wood;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    public static final int SCREEN_WIDTH = 1600;
    public static final int SCREEN_HEIGHT = 900;
    public static final int PARTICLE_SIZE = 4;
    public static final int ARR_WIDTH = SCREEN_WIDTH / PARTICLE_SIZE;
    public static final int ARR_HEIGHT = SCREEN_HEIGHT / PARTICLE_SIZE;
    public static final int CURSOR_RADIUS = 4;
    private Screen screen;
    private int currParticle;
    private int mouseX;
    private int mouseY;
    private Boolean pressed;

    public MainPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.gray);
        screen = Screen.getInstance();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        currParticle = 1;
        mouseX = 0;
        mouseY = 0;
        pressed = false;
    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;

        for (int i = 0; i < ARR_WIDTH; i++) {
            for (int j = 0; j < ARR_HEIGHT; j++) {
                g.setColor(screen.getParticle(i, j).getColor());
                g.fillRect(i * PARTICLE_SIZE, j * PARTICLE_SIZE, PARTICLE_SIZE, PARTICLE_SIZE);
            }
        }
    }

    public void update() {
        this.processInput();
        screen.update();
    }

    // private void drawCursor(Graphics g) {
    //     g = (Graphics2D) g;
    //     int centerX = mouseX / PARTICLE_SIZE;
    //     int centerY = mouseY / PARTICLE_SIZE;
    //     for (int x = centerX - CURSOR_RADIUS; x < centerX + CURSOR_RADIUS; x++) {
    //         for (int y = centerY - CURSOR_RADIUS; y < centerY + CURSOR_RADIUS; y++) {
    //             float absX = x - centerX;
    //             float absY = y - centerY; // "absolute" values of the function, 
    //             // used to discern whether or not a point is part of the circle
    //             if (x >= 0 && y >= 0 && x < ARR_WIDTH && y < ARR_HEIGHT && 
    //             absX * absX + absY * absY <= CURSOR_RADIUS * CURSOR_RADIUS) {
    //                 g.setColor(Color.magenta);
    //                 g.fillRect(x * PARTICLE_SIZE, y * PARTICLE_SIZE, PARTICLE_SIZE, PARTICLE_SIZE);
    //             }
    //         }
    //     } 
    // }

    private Particle selectedParticle(int x, int y) {
        if (currParticle == 1) {
            return new Sand(x, y);
        } else if (currParticle == 2) {
            return new Water(x, y);
        } else if (currParticle == 3) {
            return new Wood(x, y);
        } else {
            return new Air(x, y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        pressed = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            currParticle = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            currParticle = 2;
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            currParticle = 3;
        } else if (e.getKeyCode() == KeyEvent.VK_0) {
            currParticle = 0;
        }
    }

    // Adds particles in a 10x10 circle
    private void processInput() {
        if (pressed) {
            int centerX = mouseX / PARTICLE_SIZE;
            int centerY = mouseY / PARTICLE_SIZE;
            for (int x = centerX - CURSOR_RADIUS; x < centerX + CURSOR_RADIUS; x++) {
                for (int y = centerY - CURSOR_RADIUS; y < centerY + CURSOR_RADIUS; y++) {
                    float absX = x - centerX;
                    float absY = y - centerY; // "absolute" values of the function, 
                    // used to discern whether or not a point is part of the circle
                    if (x >= 0 && y >= 0 && x < ARR_WIDTH && y < ARR_HEIGHT && 
                    absX * absX + absY * absY < CURSOR_RADIUS * CURSOR_RADIUS) {
                        Particle particle = selectedParticle(x, y);
                        screen.changeValue(x, y, particle);
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

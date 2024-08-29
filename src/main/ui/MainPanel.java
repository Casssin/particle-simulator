package ui;

import javax.swing.*;

import model.Sand;
import model.Screen;
import model.Water;
import model.Air;
import model.Particle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int PARTICLE_SIZE = 2;
    public static final int ARR_WIDTH = SCREEN_WIDTH / PARTICLE_SIZE;
    public static final int ARR_HEIGHT = SCREEN_HEIGHT / PARTICLE_SIZE;
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
                g.setColor(screen.getValue(i, j).getColor());
                g.fillRect(i * PARTICLE_SIZE, j * PARTICLE_SIZE, PARTICLE_SIZE, PARTICLE_SIZE);
            }
        }
    }

    public void update() {
        screen.update();
        this.processInput();
    }

    private Particle selectedParticle(int x, int y) {
        if (currParticle == 1) {
            return new Sand(x, y);
        } else if (currParticle == 2) {
            return new Water(x, y);
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

    private void processInput() {
        if (pressed) {
            int x = mouseX / PARTICLE_SIZE;
            int y = mouseY / PARTICLE_SIZE;
            Particle particle = selectedParticle(x, y);
            screen.changeValue(x, y, particle);
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
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_1) {
            currParticle = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            currParticle = 2;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

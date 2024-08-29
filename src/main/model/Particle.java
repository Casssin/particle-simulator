package model;

import java.awt.Color;

public abstract class Particle {
    private Color color;
    protected int x;
    protected int y;

    public Particle(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void update();

}

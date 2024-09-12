package model;

import java.awt.Color;

public abstract class Particle {
    private Color color;
    protected int x;
    protected int y;
    protected Boolean hasUpdated;

    public Particle(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        hasUpdated = false;
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

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    public void notUpdated() {
        hasUpdated = false;
    }

    public Boolean hasUpdated() {
        return hasUpdated;
    }

    public abstract void update();

}

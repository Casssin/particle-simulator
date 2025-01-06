package model;

import java.awt.Color;
import java.util.Random;

public abstract class Particle {
    private Color color;
    protected int x;
    protected int y;
    protected Boolean hasUpdated;
    private Random rand;

    public Particle(Color color, int x, int y, boolean variableColor) {
        rand = new Random();
        int num = rand.nextInt(100);
        if (num < 66 || !variableColor) {
            this.color = color;
        } else if (num < 82) {
            this.color = color.darker();
        } else {
            this.color = color.brighter();
        }
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

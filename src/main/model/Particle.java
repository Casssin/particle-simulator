package model;

import java.awt.Color;
import java.util.Random;

public abstract class Particle {
    protected Color color;
    protected int x;
    protected int y;
    protected Boolean hasUpdated;
    private Random rand;
    protected Screen screen;

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
        if (!(this instanceof Air))
            screen = Screen.getInstance();
    }

    protected void swap(int x, int y) {
        Particle toBeSwapped = screen.getParticle(x, y);
        screen.changeValue(this.x, this.y, toBeSwapped);
        screen.changeValue(x, y, this);
    }

    protected void moveTo(int x, int y) {
        Particle toBeMoved = screen.getParticle(x, y);
        screen.changeValue(this.x, this.y, toBeMoved);
        this.x = x;
        this.y = y;
        screen.changeValue(this.x, this.y, this);
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

package model;

import java.awt.Color;

public abstract class Solid extends Particle {
    protected Screen screen;

    public Solid(Color color, int x, int y) {
        super(color, x, y);
        screen = Screen.getInstance();
    }

    protected void moveTo(int x, int y) {
        screen.changeValue(this.x, this.y, new Air(this.x, this.y));
        this.x = x;
        this.y = y;
        screen.changeValue(this.x, this.y, this);
    }

    public abstract void update();
}

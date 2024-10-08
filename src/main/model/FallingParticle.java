package model;

import java.awt.Color;

public abstract class FallingParticle extends Particle {
    protected Screen screen;

    public FallingParticle(Color color, int x, int y) {
        super(color, x, y);
        screen = Screen.getInstance();
    }

    protected void moveTo(int x, int y) {
        Particle toBeMoved = screen.getParticle(x, y);
        screen.changeValue(this.x, this.y, toBeMoved);
        this.x = x;
        this.y = y;
        screen.changeValue(this.x, this.y, this);
    }

    public abstract void update();
}

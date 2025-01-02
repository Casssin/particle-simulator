package model;

import java.awt.Color;

public abstract class SolidFallingParticle extends FallingParticle {
    protected float velocityY;
    public SolidFallingParticle(Color color, int x, int y) {
        super(color, x, y);
        velocityY = 0;
    }
    
    protected void swap(int x, int y) {
        Particle toBeSwapped = screen.getParticle(x, y);
        screen.changeValue(this.x, this.y, toBeSwapped);
        screen.changeValue(x, y, this);
    }

    public abstract void update();
}

package model;

import java.awt.Color;

public abstract class SolidFallingParticle extends FallingParticle {
    public SolidFallingParticle(Color color, int x, int y) {
        super(color, x, y, true);
    }
    
    protected void swap(int x, int y) {
        Particle toBeSwapped = screen.getParticle(x, y);
        screen.changeValue(this.x, this.y, toBeSwapped);
        screen.changeValue(x, y, this);
    }

    public abstract void update();
}

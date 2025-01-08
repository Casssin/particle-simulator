package model;

import java.awt.Color;

public abstract class SolidFallingParticle extends FallingParticle {
    public SolidFallingParticle(Color color, int x, int y) {
        super(color, x, y, true);
    }
    
    public abstract void update();
}

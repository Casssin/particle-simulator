package model;

import java.awt.Color;

public abstract class NonFallingParticle extends Particle {
    public NonFallingParticle(Color color, int x, int y, boolean variableColor) {
        super(color, x, y, variableColor);
    }

    public abstract void update();
}

package model;

import java.awt.Color;

public abstract class FallingParticle extends Particle {
    protected float velocityY;

    public FallingParticle(Color color, int x, int y, boolean variableColor) {
        super(color, x, y, variableColor);
        velocityY = 0.5f;
    }

    protected void fallDown(int x, int y) {
        velocityY += 0.25f;
        velocityY = calcFallBelow(y);
        this.moveTo(x, y + (int) velocityY);
    }   
    

    private float calcFallBelow(int startY) {
        for (int i = 1; i <= (int) velocityY; i++) {
            int checkY = startY + i;
            if (!screen.inBounds(x, checkY) || !screen.isGas(x, checkY)) {
                return i - 1;
            }
        }
        return velocityY;
    }

    public abstract void update();
}

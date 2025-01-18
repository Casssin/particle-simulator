package model;

import java.awt.Color;

public abstract class GasParticle extends Particle {
    protected float velocityY;

    public GasParticle(Color color, int x, int y, boolean variableColor) {
        super(color, x, y, variableColor);
        velocityY = 1f;
    }

    protected void fallUp(int x, int y) {
        velocityY -= 0.25;
        velocityY = calcFallUp(y);
        this.moveTo(x, y + (int) velocityY);
    }   
    
    private float calcFallUp(int startY) {
        for (int i = -1; i >= (int) velocityY; i--) {
            int checkY = startY + i;
            if (!screen.inBounds(x, checkY) || !screen.isGas(x, checkY)) {
                return i + 1;
            }
        }
        return velocityY;
    }

    public abstract void update();
}

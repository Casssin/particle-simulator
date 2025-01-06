package model;

import java.awt.Color;

public abstract class FallingParticle extends Particle {
    protected Screen screen;
    protected float velocityY;

    public FallingParticle(Color color, int x, int y, boolean variableColor) {
        super(color, x, y, variableColor);
        screen = Screen.getInstance();
        velocityY = 0.5f;
    }

    protected void moveTo(int x, int y) {
        Particle toBeMoved = screen.getParticle(x, y);
        screen.changeValue(this.x, this.y, toBeMoved);
        this.x = x;
        this.y = y;
        screen.changeValue(this.x, this.y, this);
    }

    protected void fallDown(int x, int y) {
        velocityY += 0.25;
        velocityY = calcFallBelow(y);
        this.moveTo(x, y + (int) velocityY);
    }
    
    private float calcFallBelow(int startY) {
        for (int i = 1; i <= (int) velocityY; i++) {
            int checkY = startY + i;
            if (!screen.inBounds(x, checkY) || !screen.isAir(x, checkY)) {
                return i - 1;
            }
        }
        return velocityY;
    }

    public abstract void update();
}

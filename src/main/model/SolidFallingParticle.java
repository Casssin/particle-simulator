package model;

import java.awt.Color;

public abstract class SolidFallingParticle extends FallingParticle {
    public SolidFallingParticle(Color color, int x, int y) {
        super(color, x, y, true);
    }
    
    // if directly below is open, go down
    // if below is blocked, but below and left is open, go there
    // if below is blocked and below and left is blocked, but below and right is
    // open, go there
    // otherwise don't go anywhere
    @Override
    public void update() {
        if (screen.isGas(x, y + 1)) {
            fallDown(x, y);
        } else if (screen.isGas(x - 1, y + 1)) {
            this.moveTo(x - 1, y + 1);
        } else if (screen.isGas(x + 1, y + 1)) {
            this.moveTo(x + 1, y + 1);
        } else if (screen.isLiquid(x, y + 1)) {
            this.swap(x, y + 1);
        } else if (screen.isLiquid(x - 1, y + 1)) {
            this.swap(x - 1, y + 1);
        } else if (screen.isLiquid(x + 1, y + 1)) {
            this.swap(x + 1, y + 1);
        } else {
            velocityY = 0;
        }

        hasUpdated = true;
    }
}

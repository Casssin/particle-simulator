package model;

import java.awt.Color;

public abstract class FallingLiquid extends FallingParticle {
    protected float velocityX;
    private float dispersionRate;

    public FallingLiquid(Color color, int x, int y, float dispersionRate) {
        super(color, x, y, false);
        velocityX = 0f;
        this.dispersionRate = dispersionRate;
    }

    protected void disperse(int direction) {
        // System.out.println("Disperse direction: " + direction + ", velocityX: " + velocityX + ", velocityY: " + velocityY);
        if ((int) velocityX * direction < 0 || (velocityX < 1f && velocityX > -1f)) {
            velocityX = direction;
        }
        if (completelySurrounded()) {
            velocityX -= (dispersionRate * direction) / 4;
            velocityY = Math.max(velocityY - 1f, 0);
            return;
        }
        velocityY += 0.25f;
        velocityX += (direction < 0 ? -dispersionRate : dispersionRate);
        
        int vel[] = calculateDisperseValue(direction);
        velocityX = vel[0];
        velocityY = vel[1];
    }

    // returns the velocity of x and y in an array
    private int[] calculateDisperseValue(int direction) {
        for (int i = 1; i <= Math.abs(velocityX); i++) {
            int ans[] = traverseMatrixAlgorithm(i);
            int x = ans[0];
            int y = ans[1];
            // if not movable here, go to last moveable location 
            if (!screen.isGas(x, y)) {
                int dest[] = traverseMatrixAlgorithm(i - 1);
                int oldX = this.x;
                int oldY = this.y;
                this.moveTo(dest[0], dest[1]);
                return new int[] {dest[0] - oldX, dest[1] - oldY};
            }
        }
        int dest[] = traverseMatrixAlgorithm((int) Math.abs(velocityX));
        int oldX = this.x;
        int oldY = this.y;
        this.moveTo(dest[0], dest[1]);
        return new int[] {dest[0] - oldX, dest[1] - oldY};
    }
    

    private int[] traverseMatrixAlgorithm(int xt) {
        int x = (velocityX < 0 ? -xt : xt);
        float m = velocityY/velocityX;
        int y = Math.round(m * x + this.y);
        return new int[] {this.x + x, y};
    }

    private boolean completelySurrounded() {
        for (int i = this.x - 1; i <= this.x + 1; i++) {
            for (int j = this.y; j <= this.y + 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (screen.isGas(i, j))
                    return false;
            }
        }
        return true;
    }
    public abstract void update();
}
